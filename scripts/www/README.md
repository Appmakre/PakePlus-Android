# TXT阅读器 - Android应用

一个简洁、轻量级的Android TXT阅读器应用。

## 功能特点

- 支持TXT文件打开和阅读
- 可调节字体大小
- 可调节行距
- 可调节字间距
- 多种背景颜色可选（米色、绿色、蓝色、黑色）
- 界面简洁，应用体积小

## 项目结构

```
阅读器/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/syzo/txtreader/
│   │       │   ├── MainActivity.java      # 主界面，文件选择
│   │       │   └── ReaderActivity.java    # 阅读器界面
│   │       ├── res/
│   │       │   ├── layout/                # 布局文件
│   │       │   ├── values/                # 颜色、字符串、主题
│   │       │   └── xml/                   # 配置文件
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## 构建说明

### 前置条件

- Android Studio (推荐最新版本)
- Android SDK (API 34)
- JDK 8 或更高版本

### 构建步骤

1. 使用Android Studio打开项目目录
2. 等待Gradle同步完成
3. 连接Android设备或启动模拟器
4. 点击Run按钮或使用命令：
   ```
   ./gradlew assembleDebug
   ```
5. APK文件位于：`app/build/outputs/apk/debug/`

### 发布构建

```
./gradlew assembleRelease
```

## 使用说明

1. 启动应用
2. 点击"选择文件"按钮
3. 选择要阅读的TXT文件
4. 在阅读界面点击"设置"按钮可调整阅读效果

## 系统要求

- Android 7.0 (API 24) 或更高版本
