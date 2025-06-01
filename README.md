Allow unauthenticated players, such as **custom bots**, to join the game _without authenticating_ through Microsoft. Use the `/allowplayer <player>` command to allow a player with that username to join in the next 60 seconds, _even if they aren't logged in_ to a valid Microsoft account. **This allows players you want on your server to join but prevents random griefers/etc. from joining.**

_Note: Should work with new-ish versions as it doesn't change much code but I've only tested it on 1.20.1, 1.20.4, and 1.21.4_

## FAQ:
- **Q:** Will this support forge?
  - **A:** It doesn't right now and I have no plans to add support at the moment.
- **Q:** Can you backport it to older versions than 1.20?
  - **A:** Probably not, but you could try just using a `fabric_loader_dependency.json` file to override it (be warned that this probably won't work)
- **Q:** Are these questions actually asked frequently?
  - **A:** I don't know, but they seem like good things to answer
- **Q:** Are you just trying to fill up space on the mod page now?
  - **A:** Yeah because it looks better this way

### Requires [Fabric API](https://modrinth.com/mod/fabric-api/)
