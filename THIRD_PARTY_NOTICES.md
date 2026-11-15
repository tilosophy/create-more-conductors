# Third-party notices

Create: More Conductors code is Copyright (c) 2026 tilosophy, MIT licensed.

## Required external mods

Steam 'n' Rails community port: https://github.com/Porters-of-Railways/Railway-1.21.1
Target source tag v0.3.0-beta.2, commit 8581312792ccb542579f3e847027cd2d8aa8e6da.
Steam 'n' Rails is Copyright (c) The Railways Team and contributors, LGPL-3.0-or-later as stated in its source headers.
This addon integrates with the separately installed mod. Its code, models, original conductor skin and caps are not bundled in the production JAR.

Create: https://github.com/Creators-of-Create/Create (MIT).
Optional casing provider mods remain separately installed. Their code and casing textures are not bundled in this addon.

## Artwork

The 12 additional body textures are original addon artwork baked from a generated material atlas into the existing conductor model's 64x64 UV layout. No upstream skin pixels are included in those textures. The original Andesite conductor and cap rendering are supplied by Steam 'n' Rails at runtime.
The generated material atlas and exact baking procedure are retained under art/ and tools/ for reproducibility.

## Build tooling

Gradle wrapper: Gradle project, Apache-2.0 (https://github.com/gradle/gradle).
Initial wrapper scripts and project conventions adapted from NeoForgeMDKs/MDK-1.21.1-ModDevGradle (CC0-1.0).
