package net.superblaubeere27.clientbase.injection.interfaces;

import net.minecraft.util.Session;

public interface IMixinMinecraft {

    Session getSession();

    void setSession(Session session);

}
