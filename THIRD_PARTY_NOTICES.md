# Third-party notices

Create: More Conductors original addon code and tooling are Copyright (c) 2026 tilosophy, MIT licensed. The root MIT license does not cover the upstream-derived texture assets identified below.

## Required external mods

Steam 'n' Rails community port: https://github.com/Porters-of-Railways/Railway-1.21.1
Target source tag v0.3.0-beta.2, commit 8581312792ccb542579f3e847027cd2d8aa8e6da.
Steam 'n' Rails is Copyright (c) The Railways Team and contributors, LGPL-3.0-or-later as stated in its source headers.
This addon integrates with the separately installed mod. Its code, models and caps are not bundled in the runtime JAR. Alpha 2 includes modified conductor texture assets as described below.

Create: https://github.com/Creators-of-Create/Create (MIT).
Optional casing provider mods remain separately installed. Their code and casing textures are not bundled in this addon.

## Artwork

Starting with alpha 2, all 12 PNGs under assets/moreconductors/textures/entity/conductor/ are modified versions of Steam 'n' Rails' conductor.png, under its declared LGPL-3.0 license. Credit: The Railways Team and contributors; community port by Porters of Railways. They are not MIT-only original artwork.

Modifications by tilosophy, 2026-11-16: material palette substitution and muted lighter eyes for Shadow and Sturdy, preserving the original layout and shading structure. The original Andesite conductor and all cap rendering continue to be supplied by the installed Steam 'n' Rails mod.

Corresponding texture source, exact upstream revision, palettes and conversion procedure are in art/upstream/, art/palettes.properties and tools/BakeTextures.java in this repository, and texture-source/ in the accompanying sources JAR. Derived comparison/lineup previews under art/ also retain LGPL-3.0 coverage. Copies of LGPLv3 and GPLv3 are included under licenses/ in both JARs.

Source repository: https://github.com/tilosophy/create-more-conductors/tree/develop/first-alpha
Upstream texture: https://github.com/Porters-of-Railways/Railway-1.21.1/blob/8581312792ccb542579f3e847027cd2d8aa8e6da/src/main/resources/assets/railways/textures/entity/conductor.png

The unused historical art/material-atlas.png from alpha 1 remains original AI-assisted MIT artwork. It is no longer used to generate any shipped skins.

## Build tooling

Gradle wrapper: Gradle project, Apache-2.0 (https://github.com/gradle/gradle).
Initial wrapper scripts and project conventions adapted from NeoForgeMDKs/MDK-1.21.1-ModDevGradle (CC0-1.0).
