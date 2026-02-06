# ChostPulse-Bukkit

Asynchronous heartbeat and statistics collector for Bukkit/Spigot servers.

## Installation

1. Download or build the `.jar` file.
2. Place it in the `plugins/` folder.
3. Restart the server.
4. On first run, the plugin will generate a unique `token` in `config.yml`.

## Configuration

```yaml
api_url: "https://your-domain.com/api/heartbeat"
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

## Contributing

Contributions are welcome and appreciated! Here's how you can contribute:

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

Please make sure to update tests as appropriate and adhere to the existing coding style.

## License

This project is licensed under the CSSM Unlimited License v2.0 (CSSM-ULv2). Please note that this is a custom license. See the [LICENSE](LICENSE) file for details.
