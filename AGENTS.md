# Create: More Conductors

## Approval and scope
- Ask the owner for explicit approval before production steps or changes, including code, textures, configuration, builds, commits, pushes, and releases.
- Research and read-only analysis are allowed when implied by the conversation.
- Approval covers the concrete batch described to the owner, including routine fixes necessary to complete and validate that batch. Ask again before expanding its scope.
- Current approved batch (2026-11-15): initial project, 13 appearances (original Andesite plus 12 designed variants), casing-based creation, persistence and multiplayer synchronization, alpha build, checks, player test checklist, and commit/push to a development branch.
- Work only in this repository. Do not change WiM, other mods, or live game/server installations.
- No public release, deployment, or merge to main without approval.

## Target
Minecraft 1.21.1; Java 21; NeoForge 21.1.262; Create 6.0.10; Steam 'n' Rails community port 0.3.0-beta.2+neoforge-mc1.21.1.
Do not silently update these versions.

## Design
Use the existing Steam 'n' Rails conductor entity, model, animations, jobs and cap colours. Variants are cosmetic.
Initial lineup: Andesite, Brass, Copper, Train, Shadow, Radiant, Zinc, Blaze, Sturdy, Industrial, Overburden, Blackstone, Platinum.
Optional casing providers must not become mandatory dependencies. No automatic casing discovery or generated-material support in v1.
Preserve upstream named special skins, healing and drops. No new casing recipes.

## Verification
Run build and focused GameTests. Check actual JAR contents. Distinguish automated checks from manual client/multiplayer checks; never claim unperformed tests passed.
Provide an alpha JAR and a clear player test checklist. Document limitations and third-party provenance.
