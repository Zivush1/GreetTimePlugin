# GreetTime Plugin

GreetTime is a simple Minecraft plugin that adds two custom commands: `/greet` and `/timecheck`.

## Features

- `/greet <player>`: Send a customizable greeting message to a specific player.
- `/timecheck`: Check the current server time.

## Installation

1. Download the latest `GreetTime.jar` file from the releases page.
2. Place the JAR file in your server's `plugins` folder.
3. Restart your server or run the command `/reload confirm` if your server supports it.

## Usage

- `/greet <player>`: Greet a specific player. Replace `<player>` with the player's name.
- `/timecheck`: Check the current server time.

## Configuration

You can customize the greeting message by editing the `config.yml` file in the plugin's folder:

```yaml
greeting-message: "&aHello, %player%! Welcome to the server!"
