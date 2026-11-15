"""Check the distributable, not only the source tree. Python 3 standard library."""
import pathlib
import struct
import zipfile

root = pathlib.Path(__file__).resolve().parents[1]
jar = root / "build/libs/create-more-conductors-0.1.0-alpha.1.jar"
variants = "brass copper train shadow radiant zinc blaze sturdy industrial overburden blackstone platinum".split()
with zipfile.ZipFile(jar) as z:
    names = z.namelist()
    assert z.testzip() is None
    assert len(names) == len(set(names)), "Duplicate archive entries"
    for name in variants:
        b = z.read(f"assets/moreconductors/textures/entity/conductor/{name}.png")
        assert b[:8] == b"\x89PNG\r\n\x1a\n"
        assert struct.unpack(">II", b[16:24]) == (64, 64), name
    textures = [n for n in names if n.endswith(".png")]
    assert len(textures) == 12, textures
    assert not any(n.startswith("assets/railways/") for n in names), "Upstream assets bundled"
    assert not any("GameTests" in n or n.startswith("data/moreconductors/") for n in names), "Test resources leaked"
    assert not any(n.endswith(".jar") for n in names), "Unexpected bundled dependency"
    meta = z.read("META-INF/neoforge.mods.toml").decode()
    assert '${version}' not in meta and 'version="0.1.0-alpha.1"' in meta
    for n in ["LICENSE", "THIRD_PARTY_NOTICES.md", "moreconductors.mixins.json"]:
        assert z.read(n)
    for n in names:
        if n.endswith(".class"):
            assert struct.unpack(">H", z.read(n)[6:8])[0] == 65, "Expected Java 21 bytecode"
print(f"PASS: {jar.name}; 12 valid skins, Java 21, correct metadata; no test mod or bundled dependencies")
