# ChostPulse-Bukkit

Asynchronous heartbeat and statistics collector for Bukkit/Spigot servers.

## Installation

1. Download or build the `.jar` file.
2. Place it in the `plugins/` folder.
3. Restart the server.
4. On first run, the plugin will generate a unique `token` in `config.yml`.

## Configuration

```yaml
api_url: "[https://your-domain.com/api/heartbeat](https://your-domain.com/api/heartbeat)"
token: "" # Generated automatically
interval: 60 # Seconds
send-software: true
debug: false
```

## Badges

Use your **Public ID** (found in server logs on startup) to display badges:

- **Status:** `https://your-domain.com/api/badge?id=YOUR_ID&type=status`
- **TPS:** `https://your-domain.com/api/badge?id=YOUR_ID&type=tps`
- **Players:** `https://your-domain.com/api/badge?id=YOUR_ID&type=players`
- **Software:** `https://your-domain.com/api/badge?id=YOUR_ID&type=software`
- **Version:** `https://your-domain.com/api/badge?id=YOUR_ID&type=version`

## License

MIT
