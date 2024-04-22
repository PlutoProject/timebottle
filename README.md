# timebottle

🫙 适用于 Paper 服务器的漂流瓶插件。

让漂流瓶来承载历史与记忆。将曾经的时刻投入大海，飘向未来。

> [!WARN]
>
> 此插件只支持在 1.19 及以上的 Paper 及其分支服务端上运行。
>
> Spigot、CraftBukkit 已不再是开设 1.13+ 服务器的主流选择。
>
> 同时，Paper 在新版本提供了大量好用的 API，可以帮助插件开发人员以高性能、高拓展性编写插件。

## 特点

- 支持 [Folia](https://github.com/PaperMC/Folia)。
- 支持多种数据存储模式（MySQL、MongoDB 等）。 
- 高度可配置性。
- 高性能，基于 Kotlin 协程打造。
- 面向开发人员的，易用的 API。

## 📦 安装与使用

待编写。

## 🔧 构建

> [!NOTE]
>
> 此处以 Linux 系统上的步骤举例。
>
> 如果您使用的是 Windows，可能需要修改部分命令。

1. 将本项目拉取到你的设备：`git clone https://github.com/PlutoProject/timebottle.git`
2. 进入项目目录：`cd ./timebottle`
3. 打包构建：`./gradlew shadowJar`
4. 在 `./build/libs` 中取出构建的结果。

## 📄️ 许可

[PlutoProject/timebottle](https://github.com/PlutoProject/timebottle)
在 [GNU Lesser General Public License v3.0](https://www.gnu.org/licenses/lgpl-3.0.html) 下许可。

![license](https://www.gnu.org/graphics/lgplv3-147x51.png)