# ScriptBlockPlus

[English](README.md) | [日本語](README-ja.md) | [简体中文](README-zh.md)

RIA-AED/ScriptBlockPlus 是 yuttyann/ScriptBlockPlus 的 fork，而 yuttyann/ScriptBlockPlus 本身又是 ScriptBlock 的 fork。
本分支由 RIA 服务器维护。

本分支保留了上游功能和附加组件整合，但做了以下调整：
- 完全移除了红石脚本方块功能
- 已适配新版本，目前测试版本为 `26.1.2`
- 构建 JDK 调整为 25，但运行时 Java 要求不变
- 修复了一些性能问题和内存溢出 BUG
- 已将官方附加组件 `ScriptEntityPlus` 直接合并进主分支

---
以下内容基本来自原仓库文档。

概述
-----------
[ScriptBlock](https://dev.bukkit.org/projects/scriptblock) 的功能被继承下来，并加入了新功能和改进。
(我们也在开发可运行于多个平台的后继插件。)

安装
-----------
将 `ScriptBlockPlus` 的 jar 放入 `plugins` 文件夹即可。

### Java8 版本
另一个 [仓库](https://github.com/yuttyann/ScriptBlockPlus-Java8) 提供了 **ScriptBlockPlus** 的 **Java8 版本**。
如果旧平台服务器上 **Java11** 无法运行，可以使用它。
不过总体上并不提供支持，并且更新已经停止很久了。
**从插件版本 `v2.1.2` 开始停止更新。**

### 联动插件
| Plugin | Description |
|:---|:---|
| [Vault](https://www.spigotmc.org/resources/vault.34315/) | 可以使用权限和经济类插件的功能。 |
| [DiscordSRV](https://www.spigotmc.org/resources/discordsrv.18494/) | 可以使用 Discord 相关功能。 |
| [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) | 可以扩展占位符功能。 |

### 内置功能
| Feature | Description |
|:---|:---|
| ScriptEntityPlus | 可以为实体设置脚本。 |

**== 各版本运行范围 ==**
| Plugin | Server | Java |
|:---|:---|:---:|
|**`2.4.1`**|**`1.9-26.1.2`**|**Java25**|
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

这个 fork 也已在 `26.1.2` 上测试过。

**== 关于版本 "1.13-1.13.1" 的运行 ==**
由于服务器不支持 **Java9** 及以上版本，**会出现错误**。
插件本身可以正常运行，但可能会发生问题，因此请使用 "[**1.13.2**](https://papermc.io/legacy)"。
**解决方法(不推荐)**：修改 `<Server>.jar` 内的 [这里](https://pastebin.com/UFBdKXJD) `org/objectweb/asm/ClassVisitor.class` 也可以运行。

**== 关于插件问题 ==**
报告的大多数问题都与数据文件或配置文件有关。
提交报告时，请先删除 `plugins/ScriptBlockPlus/json` 内的 `format.sbp` 并重新生成。
另外，更新时配置文件也可能变化，因此建议重新生成。
**(如果重新生成或删除数据文件、配置文件后仍无法解决，请到 [Issue](https://github.com/yuttyann/ScriptBlockPlus/issues) 报告。)**
`如果连启动都不行，请检查 Java 版本。`

支持的平台
-----------
只要实现了 **[`BukkitAPI`](https://hub.spigotmc.org/javadocs/bukkit/overview-summary.html)**，基本都可以运行。
如果找不到 **`net.minecraft.server`**，那么 **依赖 NMS 的功能会被强制禁用**。
下面是已经测试过的服务器列表。
| Server | Description |
|:---|:---|
|[Spigot](https://www.spigotmc.org/)|常见且稳定的基础服务器。|
|[Paper](https://papermc.io/)|Spigot 的分支，除了优化外也增加了更细致的配置。|
|[Tuinity](https://ci.codemc.io/job/Spottedleaf/job/Tuinity/)|Paper 的分支，针对大型服务器做了优化。|
|[Yatopia](https://yatopiamc.org/)|Tuinity 的分支，应用了许多平台优化补丁。|
|[Purpur](https://purpur.pl3x.net/)|Tuinity 的分支，增加了许多独特功能。|
|[Akarin](https://github.com/Akarin-project/Akarin)|Paper 的分支，以提升性能为目标开发。|
|[Mohist](https://mohistmc.com/)|同时具备 Forge 和 Spigot(Paper) 的功能。|
|[Magma](https://magmafoundation.org/)|同时具备 Forge 和 Spigot(Paper) 的功能。|


下载
-----------
| 站点 | 语言 | 说明 |
|:---|:---|:---|
| [FileArchive](https://github.com/yuttyann/FileArchive/tree/main/ScriptBlockPlus) | `Japanese` | 作者整理发布文件的仓库。 |
| [SpigotMC](https://www.spigotmc.org/resources/78413/) | `English` | 作者用于面向海外用户发布的站点。 |
| [MCBBS](https://www.mcbbs.net/thread-691900-1-1.html) | `Chinese` | 有志愿者解说和发布该插件的中国论坛。 |

链接
-----------
| 页面 | 说明 |
|:---|:---|
| [MCPoteton](https://mcpoteton.com/mcplugin-scriptblockplus) | 介绍了许多功能。 |

实体脚本功能
-----------
ScriptEntityPlus 已经合并进 ScriptBlockPlus，不需要再单独安装 `ScriptEntityPlus`。
可以为实体设置在普通点击和死亡时执行的脚本。

### 旧 ScriptEntityPlus 数据迁移
如果你以前使用的是独立的 `ScriptEntityPlus` 插件，可以把它的配置和消息文件迁移到 `ScriptBlockPlus` 目录。（旧的插件目录中不含脚本实体数据。）

1. 停止服务器。
2. 将旧 `plugins/ScriptEntityPlus/` 里的文件复制到 `plugins/ScriptBlockPlus/`。
3. 需要的话，把文件改成合并后使用的名字：
   - `config_en.yml` -> `scriptentityplus_config_en.yml`
   - `config_ja.yml` -> `scriptentityplus_config_ja.yml`
   - `config_zh.yml` -> `scriptentityplus_config_zh.yml`
   - `message_en.yml` -> `scriptentityplus_message_en.yml`
   - `message_ja.yml` -> `scriptentityplus_message_ja.yml`
   - `message_zh.yml` -> `scriptentityplus_message_zh.yml`
4. 启动服务器。

实体脚本数据保存在 `plugins/ScriptBlockPlus/json/entityscript/`，所以这部分没有独立的 `ScriptEntityPlus` 数据目录。

### 使用方法
基本上请按照工具的 **`说明文字`** 操作。
玩家输入 **命令** `/sbp tool` 后可获得 **工具** `Script Connection`。
目标指定方式是 **对方块左键**，**对实体右键**。

**权限**
工具 "Script Connection" 的使用权限: `scriptentityplus.tool.scriptconnection`

**工具模式**
将工具拿在主手时，左键即可切换模式。

**`NORMAL MODE`**
可以设置实体被点击时执行的脚本。

**`DEATH MODE`**
可以设置实体死亡时执行的脚本。

**聊天事件**
把鼠标悬停在文本上或点击文本，可以显示信息或执行命令。
(也就是所谓的 [`tellraw`](https://minecraft-ja.gamepedia.com/%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89/tellraw)。)

**`选择脚本 [MAINHAND+SHIFT+LEFT_CLICK]`**
点击绿色文本，可以选择要设置到实体上的脚本。
![ScriptTypes](https://dl.dropboxusercontent.com/s/jvfmhrvyqvs1g50/ScriptTypes.png)

**`显示已设置的脚本 [OFFHAND+RIGHT_CLICK]`**
点击绿色文本后，会把执行脚本的命令显示到聊天栏中。
![Scripts](https://dl.dropboxusercontent.com/s/tyn94f3h5x88ytz/Scripts.png)

**`实体设置 [OFFHAND+SHIFT+RIGHT_CLICK]`**
点击被橙色 `[...]` 包住的文本，可以切换设置的 `启用`、`禁用`、`显示`。
同时把鼠标悬停在浅蓝色文本上，可以看到设置说明。
![EntitySettings](https://dl.dropboxusercontent.com/s/gpjrhmilz3yxvs0/EntitySettings.png)

### 文件相关
文件管理: `由于 ScriptBlockPlus 会根据实体 UUID 保存脚本种类和坐标，`
`如果 UUID 发生变化(例如更换展示框里的物品等)，配置文件可能会残留，请注意。`

文件路径: `设置的保存位置是` **`plugins/ScriptBlockPlus/json/entityscript/....`** `。`

删除文件: `通过工具删除，或者非玩家死亡时，配置文件会被删除。`
`另外，删除实体脚本不会影响原本配置的脚本。`
