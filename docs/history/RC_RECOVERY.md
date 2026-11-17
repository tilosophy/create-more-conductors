# More Conductors recovery — 2026-11-17

## Recovered source

Repository: https://github.com/tilosophy/create-more-conductors

Base branch: `develop/first-alpha`; base commit: `445e507` (alpha.2).

The previous chat reported rc.1 preparation and a branch update, but the fetched branch still contains alpha.2. No rc.1 runtime or release packet was found in the title search. Treat the earlier rc.1 test claims as unconfirmed for this reconstructed candidate.

## Preserved decisions

- Minecraft 1.21.1, NeoForge 21.1.262, Create 6.0.10, Railways 0.3.0-beta.2+neoforge-mc1.21.1.
- Thirteen appearances including original Andesite; fixed optional-provider mappings.
- Existing Steam ’n’ Rails model, anatomy, animations and behaviour.
- User accepted the corrected conductors in game.
- Skip multiplayer testing for now. User handles screenshots.
- Launch preparation is authorized; no public release or main merge performed.

## Work completed in this recovery

- Read source, metadata, licensing notices and historical validation report.
- Recovered and visually inspected both generated promotional images.
- Prepared rc.1 version and icon metadata; preserved gameplay and texture atlases.
- Wrote release description, guide and release notes.

## Verification boundary

`./gradlew build` could not start because Java could not download Gradle 8.14.3 (network unreachable). A curl follow-up also timed out along the distribution redirect. No new compiled candidate, GameTest pass, or runtime archive verification is claimed.

Historical alpha.2 validation is retained in `VALIDATION.md`; it reports earlier compilation, optional-provider tests, palette checks and archive checks. That report is historical evidence, not a test run performed during this recovery. User gameplay acceptance supersedes its broad client-rendering pending status, but does not establish each individual scenario was tested.

## Next steps

1. In an environment with dependency-download access, run `./gradlew build`, `./gradlew runGameTestServer`, and `./gradlew runGameTestServer -PwithCasingTestMods`.
2. Run `python3 tools/verify_jar.py`; record SHA-256 hashes of both resulting JARs and the actual test results.
3. Review the recovered artwork and listing copy; add the owner’s screenshots.
4. Select the public release version and obtain approval for publication/main merge. Keep multiplayer marked deferred.

Do not relabel the old alpha.2 runtime as rc.1. A new candidate requires a real build.
