package behaviorTree.MelonMonster

import com.t13max.ai.behavior4j.BTNode
import com.t13max.ai.behavior4j.leaf.ActionNode
import com.t13max.nms.ai.agent.MonsterAgent

class MelonFight {
    Map<Integer, BTNode<MonsterAgent>> data = new HashMap<>();

    {
        data.put(2, new ActionNode2())
        data.put(4, new ActionNode4())
        data.put(6, new ActionNode6())
        data.put(8, new ActionNode8())
        data.put(10, new ActionNode10())
    }

    BTNode<MonsterAgent> getNode(int id) {
        return data.get(id)
    }

    /**
     * NODE :ActionNode
     * @Description:
     * */
    class ActionNode2 extends ActionNode<MonsterAgent> {

        @Override
        Status execute() {
            return getAgent().arrowAttack();
        }
    }

    /**
     * NODE :ActionNode
     * @Description:
     * */
    class ActionNode4 extends ActionNode<MonsterAgent> {

        @Override
        Status execute() {
            return getAgent().commonAttack();
        }
    }

    /**
     * NODE :ActionNode
     * @Description:
     * */
    class ActionNode6 extends ActionNode<MonsterAgent> {

        @Override
        Status execute() {
            return getAgent().magicAttack();
        }
    }

    /**
     * NODE :ActionNode
     * @Description:
     * */
    class ActionNode8 extends ActionNode<MonsterAgent> {

        @Override
        Status execute() {
            return getAgent().potionAttack();
        }
    }

    /**
     * NODE :ActionNode
     * @Description:
     * */
    class ActionNode10 extends ActionNode<MonsterAgent> {

        @Override
        Status execute() {
            return getAgent().potionDefend();
        }
    }
}
