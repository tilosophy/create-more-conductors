# Alpha 1 validation

Date: 2026-11-15. Build: `0.1.0-alpha.1`.

## Completed

| Check | Result |
| --- | --- |
| Java compilation and JAR build | Passed on Java 21 / Gradle 8.14.3 |
| Exact dependency environment | NeoForge 21.1.262; Create 6.0.10; Railways 0.3.0-beta.2+neoforge-mc1.21.1 |
| Baseline dedicated GameTest server | All 7 required tests passed; only required mods and their bundled libraries installed |
| Optional-provider GameTest server | All 7 required tests passed; actual creation checked for 11 installed casing variants |
| Save/load | All 13 variant IDs preserved; cap colour retained |
| Additional spawn-data serialization | All 13 variant IDs round-tripped through RegistryFriendlyByteBuf |
| Cap colours and creative consumption | All 16 colours checked with Brass; creative cap retained |
| Survival creation | Exactly one conductor, casing removed, cap consumed, cap equipped |
| Legacy/unknown data | Andesite fallback passed |
| Unsupported stone, air and chest | No new conductor, no cap or block consumed |
| Cancelled new-variant spawn | Casing and cap retained |
| Runtime archive | 12 PNGs at 64x64, correct metadata and Java 21 bytecode; no test mod, copied Railways assets or dependency JARs |
| Static body preview | Inspected all 12 new skins on the conductor cuboid UV layout |

Optional-provider test versions: Create Encased 1.10.0-fix1, Blazing Hot 0.7.4, Dreams n' Desires 2.3a-BETA. The 11 creation checks cover Andesite, Brass, Copper, Train, Shadow, Radiant, Zinc, Blaze, Sturdy, Industrial and Overburden.

The test-server logs contain Railways optional-track loot-table errors for absent companion mods. Those resource identifiers belong to the installed Railways dependency; both test servers finished successfully. This is not a claim of a warning-free log.

## Not yet verified

- Actual client rendering, hat layering, animations, shaders and resource-pack interactions. The preview is a separate renderer, not an in-game screenshot.
- Two real players, tracking-range changes, reconnects and restart persistence in WiM. The packet test verifies data serialization, not the whole multiplayer transport/render path.
- Blackstone and Platinum creation with Gadgets & Gizmos and Create Propulsion Simulated plus their physics dependencies installed. Their registry IDs and resources were inspected in the pack's mod JARs; their variant saving and packet data passed the generic tests.
- Normal conductor tasks and named special skins during real play.
- Full WiM compatibility and performance. No measured performance claim is made.

Use [TESTING.md](TESTING.md) to finish those checks before approving live use.

## Runtime artifact

`create-more-conductors-0.1.0-alpha.1.jar`

SHA-256: `d7af1c42d596fc1adf60bee294cff23408577c40e8711d5a6847df5d872920b1`

Build outputs are reproducible archives with fixed entry timestamps. Only the runtime JAR is installed in Minecraft; the sources JAR is for development.
