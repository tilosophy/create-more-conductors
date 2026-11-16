# Conductor textures — alpha 2

The twelve body skins now use exact-coordinate palette substitution of Steam 'n' Rails' original 64x64 conductor texture. There is no newly drawn anatomy, eye rim, frame border or material pattern.

## Source and reproduction

`upstream/conductor.png` is the unmodified source, pinned by SHA-256 in `tools/BakeTextures.java`. See [upstream provenance](upstream/README.md). `palettes.properties` defines each frame, inset and eye midpoint. The conversion scales each original RGB shade relative to the material's source midpoint. Neutral joint/socket pixels and transparency are preserved. Eye changes affect only the original eight eye pixels.

With Java 21, run from the repository root:

```sh
java tools/BakeTextures.java
java tools/BakeTextures.java --verify
java tools/PreviewConductors.java
```

Verification checks every output pixel against the pinned source and palette. `--verify` never modifies the textures. The source image, palettes and conversion tool are also included in the `-sources.jar`; extract `texture-source/upstream` and `texture-source/palettes.properties` into `art/`, and `texture-source/tools` into `tools/`, then use the commands above. Outputs are created under `src/main/resources/assets/moreconductors/textures/entity/conductor/`.

## Eyes

Ten skins retain the original charcoal eye pixels exactly. Shadow and Sturdy use a subdued grey-violet midpoint (`#65616e`) with the original three-tone relief and exact spacing. These eyes use ordinary body rendering: no emissive layer or glow. The lighter option makes the eyes legible without brightening the whole dark casing. Their in-game appearance under the pack's shaders still needs review.

## Previews and licensing

`conductor-comparison.png` repeats original Andesite beside each recolour. `conductor-lineup.png` shows all twelve variants. Both are static model/UV inspections without hats or animation, not in-game screenshots.

The upstream texture, derived skins and these previews are **LGPL-3.0** material; see `upstream/README.md`, `../licenses/` and `../THIRD_PARTY_NOTICES.md`. Original addon code and the new palette tools remain MIT.

`material-atlas.png` is retained solely as historical alpha-1 AI-assisted artwork, under MIT. It is no longer an input to any skin or preview.
