# SurvivalistEssentials — Project Context

## What This Is
An early-game survival progression mod. Enforces correct tool use for breaking blocks
(no tree-punching), adds early-game tool recipes, saws, vanilla-friendly tool variants,
and other mechanics to make the first day or two more immersive.

Extracted from TinkerSurvival — the non-Tinkers' Construct mechanics were pulled out
into this standalone mod, and vanilla-friendly tool equivalents were added for players
not using Tinkers' Construct. TinkerSurvival now depends on this mod and layers
Tinkers' Construct integration on top.

This separation allows SurvivalistEssentials to be updated independently and used in
modpacks without Tinkers' Construct — important given Tinkers' Construct's slow update
pace (currently still on 1.20.1).

License: MIT

## Project Structure
Multi-loader: `Common/` + `NeoForge/` + `Fabric/`

## Branch Convention
| Branch | Modloaders        |
|--------|-------------------|
| 1.20.1 | NeoForge + Fabric |
| 1.21.1 | NeoForge + Fabric |
| 26.1   | NeoForge + Fabric |

Maintained: 1.20.1, 1.21.1, 26.1

## Dependencies
- WhiteNoise (jarJar/include)
- Handbook (jarJar/include — in-game guidebook)

## Distribution
Side: both (clientRequired = true, serverRequired = true)

## Release Process
Follow the standard wendall911 release process in
`../docs/minecraft/MINECRAFT_DEVELOPMENT_NOTES.md`.
