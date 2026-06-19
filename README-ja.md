# ScriptBlockPlus

[English](README.md) | [日本語](README-ja.md) | [简体中文](README-zh.md)

RIA-AED/ScriptBlockPlus は yuttyann/ScriptBlockPlus の fork であり、yuttyann/ScriptBlockPlus 自体は ScriptBlock の fork です。
このブランチは RIA サーバーが保守しています。

この分岐では、上流の機能とアドオン統合を残しつつ、以下の調整を行っています。
- レッドストーン用スクリプトブロック機能を完全に削除
- 新しいバージョンへ適応し、現在は `26.1.2` でテスト済み
- ビルド用 JDK を 25 に変更。ただし、実行時に必要な Java 要件は従来と変わりません
- パフォーマンス改善とメモリ溢れ系のバグ修正を実施
- 公式アドオン `ScriptEntityPlus` を本体へ統合

---
以下の内容は、基本的に元のリポジトリのドキュメントを元にしています。

概要
-----------
[ScriptBlock](https://dev.bukkit.org/projects/scriptblock) の機能を引き継ぎ、新機能追加や改善を施したプラグインです。
(現在、複数のプラットフォームで動作可能な後継プラグインも開発中です。)

導入
-----------
`ScriptBlockPlus` の jar を `plugins` フォルダへ配置してください。

### Java8版
別の [リポジトリ](https://github.com/yuttyann/ScriptBlockPlus-Java8) にて、**ScriptBlockPlus** の **Java8版** を [公開](https://github.com/yuttyann/ScriptBlockPlus-Java8/releases) しています。
古いプラットフォームのサーバーで **Java11** が動作しない場合はご利用ください。
ただし、基本的にはサポートしておらず、近いうちに更新停止することをご了承ください。
**プラグインバージョン `v2.1.2` より、更新を停止しました。**

### 連携プラグイン
| Plugin | Description |
|:---|:---|
| [Vault](https://www.spigotmc.org/resources/vault.34315/) | 権限、経済系プラグインの機能を利用できます。 |
| [DiscordSRV](https://www.spigotmc.org/resources/discordsrv.18494/) | Discord の機能を利用できます。 |
| [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) | プレースホルダの機能を拡張できます。 |

### 内蔵機能
| Feature | Description |
|:---|:---|
| ScriptEntityPlus | エンティティにスクリプトを設定できます。 |

**== 各バージョンの動作範囲 ==**
| Plugin | Server | Java |
|:---|:---|:---:|
|**`2.4.0`**|**`1.9-1.21.8`**|**Java11**|
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

この fork は `26.1.2` でもテスト済みです。

**== バージョン "1.13-1.13.1" の動作について ==**
サーバーが **Java9** 以降に対応していないことが原因で、**エラーが発生する不具合があります。**
プラグイン自体は正常に動作しますが、問題が発生する可能性があるため "[**1.13.2**](https://papermc.io/legacy)" を利用してください。
**解決方法(非推奨)** として、`<Server>.jar` 内の `org/objectweb/asm/ClassVisitor.class` を [改変](https://pastebin.com/UFBdKXJD) することで動作します。

**== プラグインの不具合について ==**
報告される不具合の多くはデータファイルや設定ファイルに関するものです。
報告の際は、一度 `plugins/ScriptBlockPlus/json` 内の `format.sbp` を削除して再生成してください。
また、アップデートで設定ファイルが変更されることもあるため、再生成を推奨します。
**(データファイルや設定ファイルの再生成、削除で解決しなかった場合は [Issue](https://github.com/yuttyann/ScriptBlockPlus/issues) で報告してください。)**
`そもそも起動しない場合は、Java のバージョンを確認してください。`

対応プラットフォーム
-----------
**[`BukkitAPI`](https://hub.spigotmc.org/javadocs/bukkit/overview-summary.html) を実装しているのであれば** 基本的に動作します。
[**`net.minecraft.server`**](https://sodocumentation.net/ja/bukkit/topic/9576/nms) が見つからなかった場合は、**NMS 依存の機能が強制的に無効** になります。
下記は、動作確認を行ったサーバーの一覧です。
| Server | Description |
|:---|:---|
|[Spigot](https://www.spigotmc.org/)|可もなく不可もない、一般的に採用されているサーバーです。|
|[Paper](https://papermc.io/)|Spigot の派生サーバーで、最適化の他、詳細な設定の追加なども行われています。|
|[Tuinity](https://ci.codemc.io/job/Spottedleaf/job/Tuinity/)|Paper の派生サーバーで、大規模なサーバー向けに最適化されています。|
|[Yatopia](https://yatopiamc.org/)|Tuinity の派生サーバーで、さまざまなサーバープラットフォーム向けの最適化パッチが適用されています。|
|[Purpur](https://purpur.pl3x.net/)|Tuinity の派生サーバーで、さまざまなユニーク機能が追加されています。|
|[Akarin](https://github.com/Akarin-project/Akarin)|Paper の派生サーバーで、パフォーマンス向上を目的として作成されています。|
|[Mohist](https://mohistmc.com/)|Forge と Spigot(Paper) の両方の機能を備えています。|
|[Magma](https://magmafoundation.org/)|Forge と Spigot(Paper) の両方の機能を備えています。|


ダウンロード
-----------
| サイト | 言語 | 説明 |
|:---|:---|:---|
| [FileArchive](https://github.com/yuttyann/FileArchive/tree/main/ScriptBlockPlus) | `Japanese` | 作者が配布物をまとめているリポジトリです。 |
| [SpigotMC](https://www.spigotmc.org/resources/78413/) | `English` | 作者が海外向けに配布を行うために利用しているサイトです。 |
| [MCBBS](https://www.mcbbs.net/thread-691900-1-1.html) | `Chinese` | 有志が解説、配布を行っている中国のマインクラフトのフォーラムです。 |

リンク
-----------
| ページ | 説明 |
|:---|:---|
| [MCPoteton](https://mcpoteton.com/mcplugin-scriptblockplus) | あらゆる機能の解説をしています。 |

エンティティスクリプト機能
-----------
ScriptEntityPlus は ScriptBlockPlus に統合されました。別途 `ScriptEntityPlus` を導入する必要はありません。
エンティティに対して通常クリック時、死亡時に実行するスクリプトを設定できます。

### 旧 ScriptEntityPlus データの移行
以前に単体の `ScriptEntityPlus` プラグインを使っていた場合は、その設定・メッセージファイルを `ScriptBlockPlus` ディレクトリへ移行できます。(`旧プラグインのディレクトリには ScriptEntity のデータは含まれていません。`)

1. サーバーを停止します。
2. 旧 `plugins/ScriptEntityPlus/` のファイルを `plugins/ScriptBlockPlus/` へコピーします。
3. 必要に応じて、以下の名前へ変更します。
   - `config_en.yml` -> `scriptentityplus_config_en.yml`
   - `config_ja.yml` -> `scriptentityplus_config_ja.yml`
   - `config_zh.yml` -> `scriptentityplus_config_zh.yml`
   - `message_en.yml` -> `scriptentityplus_message_en.yml`
   - `message_ja.yml` -> `scriptentityplus_message_ja.yml`
   - `message_zh.yml` -> `scriptentityplus_message_zh.yml`
4. サーバーを起動します。

エンティティスクリプトのデータは `plugins/ScriptBlockPlus/json/entityscript/` に保存されるため、`ScriptEntityPlus` 用の別データフォルダはありません。

### 使い方
基本的にはツールの **`説明文`** に従ってください。
プレイヤーから **コマンド** `/sbp tool` を入力し、**ツール** `Script Connection` を入手してください。
対象の指定方法は **ブロックを対象とする場合は左クリック**、**エンティティを対象とする場合は右クリック** です。

**パーミッション**
ツール "Script Connection" の使用: `scriptentityplus.tool.scriptconnection`

**ツールのモード**
ツールをメインハンドに持った状態で左クリックすると、モードを切り替えられます。

**`NORMAL MODE`**
エンティティをクリックした際に実行されるスクリプトを設定できます。

**`DEATH MODE`**
エンティティが死亡した際に実行されるスクリプトを設定できます。

**チャットイベント**
テキストにカーソルを合わせたりクリックしたりすることで、情報の表示やコマンドの実行ができます。
(いわゆる [`tellraw`](https://minecraft-ja.gamepedia.com/%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89/tellraw) です。)

**`スクリプトの選択 [MAINHAND+SHIFT+LEFT_CLICK]`**
緑色のテキストをクリックすると、エンティティに設定したいスクリプトを選択できます。
![ScriptTypes](https://dl.dropboxusercontent.com/s/jvfmhrvyqvs1g50/ScriptTypes.png)

**`設定されているスクリプトの表示 [OFFHAND+RIGHT_CLICK]`**
緑色のテキストをクリックすると、スクリプトを実行するコマンドがチャットに設定されます。
![Scripts](https://dl.dropboxusercontent.com/s/tyn94f3h5x88ytz/Scripts.png)

**`エンティティの設定 [OFFHAND+SHIFT+RIGHT_CLICK]`**
橙色の `[...]` で囲まれたテキストをクリックすると、設定の `有効`、`無効`、`表示` を切り替えられます。
また、水色のテキストにカーソルを合わせると設定の説明が表示されます。
![EntitySettings](https://dl.dropboxusercontent.com/s/gpjrhmilz3yxvs0/EntitySettings.png)

### ファイル関係
ファイルの管理: `ScriptBlockPlus のスクリプトの種類と座標をエンティティの UUID を元に保存しているため、`
`UUID の変更(例: 額縁のアイテムを変更等)があった場合、設定ファイルが残存し続けるので注意してください。`

ファイルのパス: `設定の保存先は` **`plugins/ScriptBlockPlus/json/entityscript/....`** `です。`

ファイルの削除: `ツールで削除した場合、またはプレイヤー以外が死亡した場合に設定ファイルが削除されます。`
`また、エンティティのスクリプトを削除しても設定元のスクリプトには影響はありません。`
