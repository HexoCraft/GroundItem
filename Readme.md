#GroundItem [![Build Status](https://drone.io/github.com/hexosse/GroundItem/status.png)](https://drone.io/github.com/hexosse/GroundItem/latest) [![Dependency Status](https://www.versioneye.com/user/projects/56b1f33c1c89e1003039a29b/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56b1f33c1c89e1003039a29b)
GroundItem is an API which allow you to drop items on the floor that cannot be picked up by anyone.
The main reason why I created this plugin is because as Server owner most of your clients use forge. And forge client can't see item holograms like with HolographicsDisplay or even other kind of holographics plugin.

As it is designed as an API, this plugin doesn't have any commands or permissions.

However, if you are a developer, here is how you can use it :

#####Add GroundItem as a depedency (for those using maven)
```
    <repositories>
        <repository>
           <id>hexosse-repo</id>
           <url>https://raw.github.com/hexosse/maven-repo/master/</url>
       </repository>

       ...

    </repositories>
```

```
   <dependencies>
        <dependency>
            <groupId>com.github.hexosse</groupId>
            <artifactId>GroundItem</artifactId>
            <version>1.3.0</version>
            <scope>provided</scope>
        </dependency>

       ...

    </dependencies>
```

#####Create a GroundItem
```
GroundItemApi.createGroundItem(itemStack, location);
or
GroundItemApi.createGroundItem(groundItem);
```

#####Remove a GroundItem
```
GroundItemApi.removeGroundItem(itemStack, location);
or
GroundItemApi.removeGroundItem(groundItem);
```

#####List all GroundItem
```
List<GroundItem> allGroundItems = GroundItemApi.getGroundItemList();
```

#####Find a GroundItem at a specify location
```
GroundItem groundItemsAtLocation = GroundItemApi.findGroundItem(location);
```

If you feel the need to donate to this plugin as coding always take time, then simply click the button below :

[![](https://www.paypalobjects.com/en_GB/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=KWZQGM88CGSWQ)