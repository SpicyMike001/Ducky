[Modrinth](https://modrinth.com/plugin/ducky-spm)

# Ducky

Ducky is a minimal DuckDNS updater plugin for Minecraft servers.

It periodically sends update requests to DuckDNS using your domain and token, helping keep your DNS entry up to date.

All network requests are executed asynchronously to avoid impacting server performance, and updates can also be triggered manually using a simple command.

## Downloads

A single JAR works on:
- Bukkit
- Spigot
- Paper
- Purpur

## Features

- Automatic DuckDNS update requests
- Configurable update interval
- Fully asynchronous (non-blocking)
- Manual update trigger via `/ducky test`
- Lightweight & dependency-free
- Simple YAML configuration
- Logs whether DuckDNS accepted or rejected the request

## Notes

- DuckDNS always returns `OK` even if the IP address did not change
- “Update accepted” does **not** necessarily mean the IP was modified
- This plugin is **not affiliated with DuckDNS**

---

## License – All Rights Reserved

© 2026 SpicyMikeHUN. All rights reserved.

This project is **not open source**.  
You are granted permission to **view the source code only**.

### You may NOT:
- Copy or reproduce this code
- Modify this code
- Distribute, share, or publish this code
- Use this code in any project (commercial or non-commercial) without explicit written permission

All other rights are reserved by the copyright holder.
