# ScriptBlockPlus

[English](README.md) | [日本語](README-ja.md) | [简体中文](README-zh.md)

RIA-AED/ScriptBlockPlus is a fork of yuttyann/ScriptBlockPlus, which itself is a fork of ScriptBlock.
This branch is maintained by the RIA server.

This branch keeps the upstream feature set and addon integration, but:
- removes redstone script block support entirely
- is adapted for newer versions and currently tested on `1.21.4` and `26.1.2`
- builds Java 11 bytecode for compatibility across supported server runtimes
- includes performance fixes and memory overflow bug fixes
- merges the official `ScriptEntityPlus` addon into the main plugin

---
The following content is mostly from the upstream repository documentation.

Overview
-----------
[ScriptBlock](https://dev.bukkit.org/projects/scriptblock) was extended with new features and improvements.
(We are also working on a successor plugin that can run on multiple platforms.)

Installation
-----------
Place the `ScriptBlockPlus` jar into the `plugins` folder.

### Java 8 version
A separate [repository](https://github.com/yuttyann/ScriptBlockPlus-Java8) provides a **Java 8 version** of **ScriptBlockPlus**.
Use it if **Java 11** does not run on older platforms.
However, it is not actively supported in general, and updates were stopped a long time ago.
**Updates were stopped starting from plugin version `v2.1.2`.**

### Integration plugins
| Plugin | Description |
|:---|:---|
| [Vault](https://www.spigotmc.org/resources/vault.34315/) | Lets you use permission and economy plugin features. |
| [DiscordSRV](https://www.spigotmc.org/resources/discordsrv.18494/) | Lets you use Discord-related features. |
| [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) | Extends placeholder support. |

### Built-in features
| Feature | Description |
|:---|:---|
| ScriptEntityPlus | Lets you assign scripts to entities. |

**== Compatibility by version ==**
| Plugin | Server | Java |
|:---|:---|:---:|
|**`2.4.2`**|**`1.9-26.1.2`**|**Java11**|
|`2.3.3`|`1.9-1.21.5`|**Java11**|
|`2.3.0-2.3.2`|`1.9-1.21`|**Java11**|
|`2.2.7-2.2.8`|`1.9-1.20.2`|**Java11**|
|`2.2.6`|`1.9-1.20.1`|**Java11**|
|`2.2.5`|`1.9-1.19.3`|**Java11**|
|`2.2.3-2.2.4`|`1.9-1.19.2`|**Java11**|
|`2.2.0-2.2.2`|`1.9-1.18`|**Java11**|
|`2.1.5-2.1.8`|`1.9-1.17.1`|**Java11**|
|`2.1.2-2.1.4`|`1.9-1.17`|**Java11**|
|`2.0.4-2.1.1`|`1.9-1.16.5`|**Java11**|
|[`2.x.x-JV8`](https://github.com/yuttyann/ScriptBlockPlus-Java8)|`1.9-1.16.5`|**Java8**|
|`1.8.5-2.0.3`|`1.9-1.16.5`|**Java8**|
|`1.6.0-1.8.4`|`1.8-1.15.2`|**Java8**|
|`1.4.0-1.5.0`|`1.7.2-1.13.2`|**Java8**|
|`1.0.0-1.3.3`|`1.7.2-1.13.2`|**Java7**|

This fork has also been tested on `1.21.4` and `26.1.2`.

**== About version "1.13-1.13.1" ==**
Because the server does not support **Java 9** or later, an **error can occur**.
The plugin itself works normally, but problems may happen, so please use "[**1.13.2**](https://papermc.io/legacy)".
**Workaround (not recommended)**: modify [this part](https://pastebin.com/UFBdKXJD) of `org/objectweb/asm/ClassVisitor.class` inside `<Server>.jar`.

**== About plugin issues ==**
Most reported issues are related to data files or configuration files.
When reporting, delete `format.sbp` inside `plugins/ScriptBlockPlus/json` and regenerate it once.
Also, configuration files may change during updates, so regeneration is recommended.
**If regeneration or deletion of data/config files does not help, report the issue on [Issue](https://github.com/yuttyann/ScriptBlockPlus/issues).**
`If the server does not start at all, check the Java version.`

Supported platforms
-----------
If a platform implements **[`BukkitAPI`](https://hub.spigotmc.org/javadocs/bukkit/overview-summary.html)**, it should basically work.
If **`net.minecraft.server`** cannot be found, **NMS-dependent features are forcibly disabled**.
Below is a list of servers that were tested.
| Server | Description |
|:---|:---|
|[Spigot](https://www.spigotmc.org/)|A standard server, widely used and reasonably reliable.|
|[Paper](https://papermc.io/)|A Spigot fork with extra optimizations and more detailed configuration options.|
|[Tuinity](https://ci.codemc.io/job/Spottedleaf/job/Tuinity/)|A Paper fork optimized for large-scale servers.|
|[Yatopia](https://yatopiamc.org/)|A Tuinity fork that applies many platform optimization patches.|
|[Purpur](https://purpur.pl3x.net/)|A Tuinity fork with many unique features added.|
|[Akarin](https://github.com/Akarin-project/Akarin)|A Paper fork created with performance improvements in mind.|
|[Mohist](https://mohistmc.com/)|Provides both Forge and Spigot(Paper) features.|
|[Magma](https://magmafoundation.org/)|Provides both Forge and Spigot(Paper) features.|


Download
-----------
| Site | Language | Description |
|:---|:---|:---|
| [FileArchive](https://github.com/yuttyann/FileArchive/tree/main/ScriptBlockPlus) | `Japanese` | The repository used by the author to organize distribution files. |
| [SpigotMC](https://www.spigotmc.org/resources/78413/) | `English` | The site used by the author to distribute to overseas users. |
| [MCBBS](https://www.mcbbs.net/thread-691900-1-1.html) | `Chinese` | A Minecraft forum where volunteers explain and distribute the plugin in China. |

Links
-----------
| Page | Description |
|:---|:---|
| [MCPoteton](https://mcpoteton.com/mcplugin-scriptblockplus) | Explains many of the plugin's features. |

Entity Script Feature
-----------
ScriptEntityPlus has been merged into ScriptBlockPlus. There is no need to install `ScriptEntityPlus` separately.
You can configure scripts that run on normal click and on death for entities.

### Migrating old ScriptEntityPlus data
If you previously used the standalone `ScriptEntityPlus` plugin, you can migrate its config and message files into the `ScriptBlockPlus` directory. (The old plugin directory does not contain script entity data.)

1. Stop the server.
2. Copy your old `plugins/ScriptEntityPlus/` files into `plugins/ScriptBlockPlus/`.
3. Rename them to the merged plugin names if needed:
   - `config_en.yml` -> `scriptentityplus_config_en.yml`
   - `config_ja.yml` -> `scriptentityplus_config_ja.yml`
   - `config_zh.yml` -> `scriptentityplus_config_zh.yml`
   - `message_en.yml` -> `scriptentityplus_message_en.yml`
   - `message_ja.yml` -> `scriptentityplus_message_ja.yml`
   - `message_zh.yml` -> `scriptentityplus_message_zh.yml`
4. Start the server.

Entity script data is stored under `plugins/ScriptBlockPlus/json/entityscript/`, so there is no separate `ScriptEntityPlus` data directory for that part.

### How to use
Basically, follow the tool's **`description`**.
Enter the **command** `/sbp tool` from the player and obtain the **tool** `Script Connection`.
The target selection method is **left click for blocks** and **right click for entities**.

**Permission**
Using the tool "Script Connection": `scriptentityplus.tool.scriptconnection`

**Tool mode**
You can switch modes by left-clicking while holding the tool in your main hand.

**`NORMAL MODE`**
Lets you configure scripts that run when the entity is clicked.

**`DEATH MODE`**
Lets you configure scripts that run when the entity dies.

**Chat event**
Hovering over or clicking text lets you show information or run commands.
(This is the so-called [`tellraw`](https://minecraft-ja.gamepedia.com/%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89/tellraw).)

**`Select a script [MAINHAND+SHIFT+LEFT_CLICK]`**
Click the green text to select the script you want to assign to the entity.
![ScriptTypes](https://dl.dropboxusercontent.com/s/jvfmhrvyqvs1g50/ScriptTypes.png)

**`Show assigned scripts [OFFHAND+RIGHT_CLICK]`**
Click the green text to set the command that runs the script into chat.
![Scripts](https://dl.dropboxusercontent.com/s/tyn94f3h5x88ytz/Scripts.png)

**`Entity settings [OFFHAND+SHIFT+RIGHT_CLICK]`**
Click the orange text wrapped in `[...]` to toggle settings between `enabled`, `disabled`, and `display`.
You can also hover over the light-blue text to see the setting description.
![EntitySettings](https://dl.dropboxusercontent.com/s/gpjrhmilz3yxvs0/EntitySettings.png)

### File handling
File management: `Because ScriptBlockPlus stores the script type and coordinates for entities based on their UUID,`
`be careful that configuration files may remain if the UUID changes (for example, when changing the item in an item frame).`

File path: `The save destination for settings is` **`plugins/ScriptBlockPlus/json/entityscript/....`** `.` 

File deletion: `When using the tool to delete, or when something other than a player dies, the configuration file is removed.`
`Also, deleting the entity script does not affect the original configured script.`
