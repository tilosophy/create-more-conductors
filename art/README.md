# Conductor materials

`material-atlas.png` is original AI-assisted pixel artwork, refined against the actual casing palettes. It contains twelve panels in reading order: Brass, Copper, Train, Shadow, Radiant, Zinc, Blaze, Sturdy, Industrial, Overburden, Blackstone, Platinum. It is covered by this repository's MIT license.

The art direction is a compact metal frame and inset material: dark wood/brass; charcoal ribs/copper; dark teal/brass; near-black purple/shadow; muted sage/ivory; charcoal/mint zinc; burgundy/gold blaze; black/pale lavender sturdy; dark green/sage industrial; navy/iron brackets overburden; teal/blackstone; charcoal/cyan platinum. No emissive effects are applied.

The generated atlas is the editable source, not a Minecraft texture atlas. `tools/BakeTextures.java` deterministically samples it into the existing conductor model's 64x64 UV layout and adds readable eyes. It uses no upstream texture pixels. Regenerate with Java 21 from the repository root:

```sh
java tools/BakeTextures.java
```

The original Andesite skin is loaded from the installed Railways mod. Hats, models, animations and named special skins remain supplied by Railways. The twelve new body skins are cosmetic; they do not change conductor abilities or drops.
