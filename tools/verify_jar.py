"""Check the distributable, not only the source tree. Python 3 standard library."""
import pathlib
import struct
import zipfile

root = pathlib.Path(__file__).resolve().parents[1]
version = next(line.split("=", 1)[1].strip() for line in (root / "gradle.properties").read_text().splitlines() if line.startswith("mod_version="))
jar = root / f"build/libs/create-more-conductors-{version}.jar"
variants = "brass copper train shadow radiant zinc blaze sturdy industrial overburden blackstone platinum".split()
with zipfile.ZipFile(jar) as z:
    names = z.namelist()
    assert z.testzip() is None
    assert len(names) == len(set(names)), "Duplicate archive entries"
    for name in variants:
        b = z.read(f"assets/moreconductors/textures/entity/conductor/{name}.png")
        assert b[:8] == b"\x89PNG\r\n\x1a\n"
        assert struct.unpack(">II", b[16:24]) == (64, 64), name
        assert b == (root / f"src/main/resources/assets/moreconductors/textures/entity/conductor/{name}.png").read_bytes(), "Stale skin in JAR"
    textures = [n for n in names if n.endswith(".png")]
    assert len(textures) == 12, textures
    assert not any(n.startswith("assets/railways/") for n in names), "Must not override the upstream resource namespace"
    assert not any("GameTests" in n or n.startswith("data/moreconductors/") for n in names), "Test resources leaked"
    assert not any(n.endswith(".jar") for n in names), "Unexpected bundled dependency"
    meta = z.read("META-INF/neoforge.mods.toml").decode()
    assert '${version}' not in meta and f'version="{version}"' in meta
    assert "LGPL-3.0" in meta
    for n in ["LICENSE", "THIRD_PARTY_NOTICES.md", "moreconductors.mixins.json", "licenses/LGPL-3.0.txt", "licenses/GPL-3.0.txt"]:
        assert z.read(n)
    for n in names:
        if n.endswith(".class"):
            assert struct.unpack(">H", z.read(n)[6:8])[0] == 65, "Expected Java 21 bytecode"
with zipfile.ZipFile(root / f"build/libs/create-more-conductors-{version}-sources.jar") as z:
    for name in ["texture-source/upstream/conductor.png", "texture-source/upstream/README.md", "texture-source/palettes.properties", "texture-source/tools/BakeTextures.java", "licenses/LGPL-3.0.txt", "licenses/GPL-3.0.txt"]:
        assert z.read(name), "Missing texture source or licence"
print(f"PASS: {jar.name}; 12 current skins, Java 21, metadata and licences; no test mod/dependencies; editable texture sources included in sources JAR")
