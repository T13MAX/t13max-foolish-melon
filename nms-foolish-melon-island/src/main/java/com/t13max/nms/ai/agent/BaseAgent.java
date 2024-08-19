package com.t13max.nms.ai.agent;

import com.t13max.ai.Blackboard;
import com.t13max.ai.btree.BehaviorTree;
import com.t13max.ai.btree.utils.BehaviorTreeManager;
import com.t13max.common.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础代理类
 *
 * @Author: t13max
 * @Since: 11:43 2024/7/20
 */
public abstract class BaseAgent<T> {

    protected T owner;

    private BehaviorTree<BaseAgent<T>> behaviorTree;

    private final Map<String, Object> constantData = new HashMap<>();

    public BaseAgent(T owner) {
        this.owner = owner;

    }

    protected abstract Blackboard getBlackBord();

    protected abstract long getTime();

    public void createBehaviorTree(String btName) {
        Class<?> btAgentType = BehaviorTreeManager.getInstance().getBTType(btName);
        if (btAgentType != null && !btAgentType.isAssignableFrom(getClass())) {
            Log.ai.error("绑定无效的行为树: {}=>Agent：{} 不是行为树{}=>Agent:{} 的子类型", owner, getClass().getSimpleName(), btName, btAgentType.getSimpleName());
        }

        behaviorTree = BehaviorTreeManager.getInstance().createBehaviorTree(btName);
        behaviorTree.setOwner(this);
    }

    public void update() {
        
        if (behaviorTree != null) {
            try {
                behaviorTree.update();
            } catch (Exception e) {
                Log.ai.error("行为树{}执行出现异常！！！", behaviorTree.getName());
                e.printStackTrace();
            }
        }

    }

    public void eventFire(int eventKey, Object... param) {
        if (behaviorTree != null)
            behaviorTree.handle(eventKey, param);
    }

    public void log(String message) {
        if (Log.ai.isDebugEnabled()) {
            Log.ai.debug(message);
        }
    }

    public void setBlackBordIntValue(String key, int value, int millis) {
        getBlackBord().set(key, value, millis);
    }

    public void setBlackBordFloatValue(String key, float value, int millis) {
        getBlackBord().set(key, value, millis);
    }

    public void setLocalInt(String key, int value) {
        this.constantData.put(key, value);
    }

    public void setLocalLong(String key, long value) {
        this.constantData.put(key, value);
    }

    public void setLocalFloat(String key, float value) {
        this.constantData.put(key, value);
    }

    public int getLocalInt(String key) {
        Integer value = (Integer) this.constantData.get(key);
        return value == null ? 0 : value;
    }

    public long getLocalLong(String key) {
        Long value = (Long) this.constantData.get(key);
        return value == null ? 0 : value;
    }

    public float getLocalFloat(String key) {
        Float value = (Float) this.constantData.get(key);
        return value == null ? 0 : value;
    }

    public void removeLocal(String key) {
        this.constantData.remove(key);
    }

    public int countingAndGet(String key) {
        int val = (int) constantData.getOrDefault(key, 0);
        ++val;
        constantData.put(key, val);

        return val;
    }

    public int decreasingAndGet(String key) {
        int val = (int) constantData.getOrDefault(key, 0);
        --val;
        constantData.put(key, val);

        return val;
    }

    public int getBlackBordIntValue(String key) {
        return getBlackBord().getIntValue(key).orElse(-1);
    }

    public float getBlackBordFloatValue(String key) {
        return getBlackBord().getFloatValue(key).orElse(0.f);
    }

}
