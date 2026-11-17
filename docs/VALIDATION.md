# Beta 1 validation

Date: 2026-11-17. Candidate: `0.1.0-beta.1`.

## Completed on this candidate

| Check | Result |
| --- | --- |
| Fresh runtime and sources build | Passed; Java 21, Gradle 8.14.3 |
| Baseline dedicated GameTests | All 7 required tests passed; creation from 6 core casings |
| Optional-provider dedicated GameTests | All 7 required tests passed; creation from 11 installed casings |
| Texture verification | All 4096 coordinates on each of 12 skins match the pinned source/palette procedure |
| Accepted gameplay and skins | Java sources and body textures unchanged from alpha-2 commit `445e507` |
| Runtime archive | Correct beta metadata, icon, 12 skins, Java 21 bytecode and licences; no bundled dependencies or test mod |
| Sources archive | Editable original texture, palettes, conversion tool and licence texts present |
| Documentation | Current local links checked; historical validation moved to `docs/history` |

Environment: Minecraft 1.21.1, NeoForge 21.1.262, Create 6.0.10, Steam 'n' Rails community port 0.3.0-beta.2+neoforge-mc1.21.1. Optional-provider run: Create Encased 1.10.0-fix1, Blazing Hot 0.7.4, Dreams n' Desires 2.3a-BETA.

The seven GameTests cover casing creation/consumption, all cap colours and creative cap retention, variant save/load, additional spawn-data serialization, legacy/unknown fallback, unsupported blocks, and cancelled spawning. Save/load and payload checks include all 13 variant IDs. Actual creation checks include Andesite, Brass, Copper, Train, Shadow, Radiant, Zinc, Blaze, Sturdy, Industrial and Overburden.

Both runs completed successfully. Logs also contain dependency resource errors, including Railways optional track loot tables and Create Encased slicer loot tables referencing absent items, plus development warnings. These are not warning-free logs. Full logs accompany the review packet.

## Remaining manual checks and limits

- Owner accepted alpha-2 gameplay and appearance. A final beta client startup/icon check and screenshots remain with the owner; see [checklist](TESTING.md).
- Real two-player testing is deferred. Serialization tests do not verify the full multiplayer tracking/render path.
- Blackstone and Platinum creation with their complete optional dependency stacks has not been tested; generic persistence/payload tests include their IDs.
- No full WiM compatibility or performance measurement is claimed.
- Publication, merge to `main`, and live deployment require separate owner approval.

Artifact hashes: [SHA256SUMS.txt](SHA256SUMS.txt). Historical alpha-2 evidence: [history](history/ALPHA_2_VALIDATION.md).
