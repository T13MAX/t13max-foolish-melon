package com.t13max.nms.ai.agent;


import com.t13max.ai.Blackboard;
import com.t13max.ai.behavior4j.BTNode;
import com.t13max.common.util.Log;
import com.t13max.nms.entity.MelonEntity;
import com.t13max.util.RandomUtil;
import com.t13max.util.TimeUtil;


/**
 * 智能代理
 * 拥有各种逻辑
 *
 * @Author: t13max
 * @Since: 11:52 2024/7/20
 */
public class SmartAgent<T extends MelonEntity> extends BaseAgent<T> {


    private long finishMills;

    public SmartAgent(T owner) {
        super(owner);
    }

    @Override
    public void update() {

        super.update();
    }

    @Override
    protected Blackboard getBlackBord() {
        return null;
    }

    @Override
    protected long getTime() {
        return 0;
    }

    public BTNode.Status searchEnemy() {
        Log.ai.info("searchEnemy");
        int hasTarget = getLocalInt("hasTarget");
        if (hasTarget == 1) return BTNode.Status.BT_SUCCESS;
        if (RandomUtil.getRanNumByInterval(5) == 0) {
            setLocalInt("hasTarget", 1);
        }
        return BTNode.Status.BT_SUCCESS;
    }

    public BTNode.Status arrowAttack() {
        long nowMills = TimeUtil.nowMills();
        if (finishMills == 0) {
            Log.ai.info("arrowAttack start!");
            finishMills = nowMills + 20;
        }

        if (nowMills <= finishMills) {
            Log.ai.info("arrowAttack running!");
            return BTNode.Status.BT_RUNNING;
        }
        finishMills = 0;
        Log.ai.info("arrowAttack success!");
        return BTNode.Status.BT_SUCCESS;
    }

    public BTNode.Status commonAttack() {
        long nowMills = TimeUtil.nowMills();
        if (finishMills == 0) {
            Log.ai.info("commonAttack start!");
            finishMills = nowMills + 20;
        }

        if (nowMills <= finishMills) {
            Log.ai.info("commonAttack running!");
            return BTNode.Status.BT_RUNNING;
        }
        finishMills = 0;
        Log.ai.info("commonAttack success!");
        return BTNode.Status.BT_SUCCESS;
    }

    public BTNode.Status magicAttack() {
        long nowMills = TimeUtil.nowMills();
        if (finishMills == 0) {
            Log.ai.info("magicAttack start!");
            finishMills = nowMills + 20;
        }

        if (nowMills <= finishMills) {
            Log.ai.info("magicAttack running!");
            return BTNode.Status.BT_RUNNING;
        }
        finishMills = 0;
        Log.ai.info("magicAttack success!");
        return BTNode.Status.BT_SUCCESS;
    }

    public BTNode.Status potionAttack() {
        long nowMills = TimeUtil.nowMills();
        if (finishMills == 0) {
            Log.ai.info("potionAttack start!");
            finishMills = nowMills + 20;
        }

        if (nowMills <= finishMills) {
            Log.ai.info("potionAttack running!");
            return BTNode.Status.BT_RUNNING;
        }
        finishMills = 0;
        Log.ai.info("potionAttack success!");
        return BTNode.Status.BT_SUCCESS;
    }

    public BTNode.Status potionDefend() {

        long nowMills = TimeUtil.nowMills();
        if (finishMills == 0) {
            Log.ai.info("potionDefend start!");
            finishMills = nowMills + 20;
        }

        if (nowMills <= finishMills) {
            Log.ai.info("potionDefend running!");
            return BTNode.Status.BT_RUNNING;
        }
        finishMills = 0;
        Log.ai.info("potionDefend success!");
        return BTNode.Status.BT_SUCCESS;
    }

}
