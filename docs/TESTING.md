# Alpha 2 player checklist

Build: **0.1.0-alpha.2**. Target: Minecraft 1.21.1, NeoForge 21.1.262, Create 6.0.10, Steam 'n' Rails community port 0.3.0-beta.2+neoforge-mc1.21.1.

## Set up

1. Use a separate test instance or a copy of your WiM world. Keep an untouched copy before opening it with the alpha.
2. Put the runtime JAR in `mods` on every test client and the test server. Do not install the sources JAR. Keep your existing casing providers and Shadow/Radiant recipe datapack.
3. Fully restart the game and server. No new world, new chunks or datapack reload is needed for the addon itself.

## Alpha 2 visual focus

Replace the alpha-1 runtime JAR with alpha 2; do not keep both installed. Existing variant conductors should pick up the new skins automatically after restarting. Compare each with Andesite: eye spacing, eye height, arm divisions, torso borders and leg panels should match. Shadow and Sturdy now use muted grey-violet eyes; check readability in daylight and in your shader-lit interiors. Other variants retain the original dark eyes.

## Test in this order

1. **Startup and appearance.** Place one of every casing listed below and apply a conductor's cap. Each should create exactly one conductor with the matching body material and a correctly rendered cap. Look at the front, back, arms and legs while it walks. Watch for missing purple/black textures, invisible faces, misplaced eyes or texture seams. Test with your usual resource packs and shaders after a plain-rendering check.
2. **Costs and cap colours.** In survival, each creation should remove one casing and one cap. In creative, the casing becomes a conductor and the cap remains. Try at least two differently coloured caps on the same casing: only the cap colour should differ. Check one original Andesite conductor as a comparison.
3. **Normal conductor behaviour.** Use the new variants for your usual conductor tasks alongside an Andesite conductor. Check interaction, following/positioning and the train or redstone task you normally use. The addon should not change their abilities, healing or drops. If you use a named special conductor skin, it should retain priority.
4. **Saving.** Leave several different variants together, save and quit, then reopen the world. Their bodies, cap colours and jobs should remain. Repeat after a server restart.
5. **Multiplayer.** Have a second player watch you create a conductor. Both should immediately see the same material and cap. Have the second player leave tracking range and return, then disconnect and reconnect. Recheck after a restart. This verifies the real client/server path beyond the automated packet-data tests.
6. **Unrelated blocks.** Try a cap on stone and a chest. Neither should turn into a conductor or consume the cap. Unsupported casing types should retain their existing behaviour.

| Material | Provider | Created | Appearance | Reload | Second player |
| --- | --- | --- | --- | --- | --- |
| Andesite | Create | ☐ | ☐ | ☐ | ☐ |
| Brass | Create | ☐ | ☐ | ☐ | ☐ |
| Copper | Create | ☐ | ☐ | ☐ | ☐ |
| Train | Create | ☐ | ☐ | ☐ | ☐ |
| Shadow | Create | ☐ | ☐ | ☐ | ☐ |
| Radiant | Create | ☐ | ☐ | ☐ | ☐ |
| Zinc | Create Encased | ☐ | ☐ | ☐ | ☐ |
| Blaze | Blazing Hot | ☐ | ☐ | ☐ | ☐ |
| Sturdy | Blazing Hot | ☐ | ☐ | ☐ | ☐ |
| Industrial | Dreams n' Desires | ☐ | ☐ | ☐ | ☐ |
| Overburden | Dreams n' Desires | ☐ | ☐ | ☐ | ☐ |
| Blackstone | Gadgets & Gizmos | ☐ | ☐ | ☐ | ☐ |
| Platinum | Create Propulsion Simulated | ☐ | ☐ | ☐ | ☐ |

## Report a problem

Note the casing, cap colour, singleplayer/multiplayer, action immediately before the problem, and whether it persists after rejoining. Include a screenshot for visual problems, `logs/latest.log`, and the matching crash report if there is one. For multiplayer, include both the affected client's and server's logs.

## Rollback

Stop the test game/server and restore your untouched world copy, then remove the alpha from all test installations. Do not remove casing-provider mods from a world as part of this test. The addon uses existing Railways conductor entities, but restoring the copy is the reliable way to undo test-world changes. Live deployment needs separate approval.

Automated verification and its limits are recorded in [VALIDATION.md](VALIDATION.md).
