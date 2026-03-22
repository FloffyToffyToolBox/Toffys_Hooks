package floffy.toffys_hooks.util;

import floffy.toffys_hooks.entity.HookEntity;

public interface PlayerWithHookData {
    HookEntity getHook();
    boolean isOnWall();

    void setHook(HookEntity hookEntity);
    void setOnWall(boolean bool);
}
