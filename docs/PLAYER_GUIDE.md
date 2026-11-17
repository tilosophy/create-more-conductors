# Create: More Conductors — Player Guide

## Installation

1. Use Minecraft 1.21.1, Java 21, NeoForge 21.1.262 (the build target), Create 6.0.10 and Steam ’n’ Rails community port 0.3.0-beta.2+neoforge-mc1.21.1. The declared NeoForge range allows later 21.1 builds, but this is not a claim that each has been tested.
2. Close Minecraft and stop your server before replacing the previous More Conductors JAR. Keep a world backup before updating an existing installation.
3. Put the runtime JAR into the instance’s `mods` folder. Install the same version on the server and every client. Remove the previous More Conductors JAR so only one remains. Do not install the `-sources.jar`.
4. Restart the server and clients. Existing worlds can be used; no new chunks are required.

Beta filename: `create-more-conductors-0.1.0-beta.1.jar`. See [verification status](VALIDATION.md) for checks and limitations.

## Create a conductor

1. Place a supported casing block.
2. Hold a Steam ’n’ Rails conductor’s cap in the colour you want.
3. Right-click the casing with the cap. The casing becomes a conductor with the corresponding body appearance.

In Survival this consumes one casing and one cap. In Creative the placed casing is consumed but the held cap remains. Body appearance and cap colour are independent.

## Supported casings

| Appearance | Casing ID | Provider |
| --- | --- | --- |
| Andesite (original) | `create:andesite_casing` | Create |
| Brass | `create:brass_casing` | Create |
| Copper | `create:copper_casing` | Create |
| Train | `create:railway_casing` | Create |
| Shadow | `create:shadow_steel_casing` | Create |
| Radiant | `create:refined_radiance_casing` | Create |
| Zinc | `createcasing:zinc_casing` | Create Encased |
| Blaze | `blazinghot:blaze_casing` | Blazing Hot |
| Sturdy | `blazinghot:sturdy_casing` | Blazing Hot |
| Industrial | `dndesires:industrial_casing` | Dreams n’ Desires |
| Overburden | `dndesires:overburden_casing` | Dreams n’ Desires |
| Blackstone | `createthrusters:blackstone_casing` | Gadgets & Gizmos |
| Platinum | `createpropulsion:platinum_casing` | Create Propulsion Simulated |

Optional providers are separate mods. Only the listed blocks are supported; this is not automatic support for every casing. Shadow and Radiant may need recipes from your existing datapacks. This addon supplies no casing recipes.

## What stays familiar

Conductor jobs, cap interactions, healing and drops remain supplied by Steam ’n’ Rails. A special material does not give extra powers or return that material when the conductor dies. Named special skins take priority over material skins.

The addon stores the appearance with the conductor. Old conductors or unknown appearance IDs use Andesite as their fallback. Save/load and spawn-data serialization have historical automated test coverage; a two-player session remains intentionally untested.

## Troubleshooting

- **Nothing happens:** check that you are using a conductor’s cap and an exact supported casing. The relevant optional provider must be installed. Casings with a block entity are deliberately rejected to avoid consuming containers.
- **The game reports a missing/incompatible dependency:** check the exact versions above and remove duplicate JARs.
- **A named conductor looks different:** Steam ’n’ Rails special named skins have priority.
- **A recipe is missing:** check the casing provider or pack datapack. More Conductors adds appearances, not recipes.

## Screenshots

Suggested shots: a lineup with matching cap colours; a conductor beside its matching casing; a mixed-material crew at a station. Use actual game captures for gameplay claims. The supplied workshop banner and circular badge are promotional illustrations.
