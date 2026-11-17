# Beta 1 checks

The owner accepted alpha-2 gameplay and appearance. Beta 1 preserves those Java sources and skins; it changes version metadata, branding and documentation.

## Owner verification status

On 2026-11-17 the owner confirmed the beta mod-list icon displays correctly in game and screenshots are complete, and approved merging to `main`. The checklist below is retained for reference; this report does not claim every individual scenario was separately tested.

## Client checklist for reference

1. Replace the previous More Conductors runtime JAR with `create-more-conductors-0.1.0-beta.1.jar` in a test instance. Keep only one version and restart.
2. Confirm startup and the mod-list icon. Open the existing test world and check a previously placed conductor, then create a Brass conductor with a cap.
3. Capture an in-game lineup, a conductor next to its matching casing, and a mixed crew at a station. Use the owner's preferred shaders. Promotional art is not an in-game screenshot.

Screenshots are complete and held by the owner for the listing. A real two-player test is deferred, not a release-blocking requirement imposed by this checklist. Do not claim multiplayer verification.

## Optional future multiplayer check

With the same runtime on both clients and server, have a second player watch creation, leave tracking range and return, reconnect, and check after restart. Confirm body, cap colour and jobs agree on both clients.

For problems include the casing, cap colour, steps, client/server logs and screenshots. Keep a world backup before installation; stop the instance and restore that backup plus the previous mod version to roll back.

See [current validation](VALIDATION.md) and [historical alpha-2 checklist](history/ALPHA_2_TESTING.md).
