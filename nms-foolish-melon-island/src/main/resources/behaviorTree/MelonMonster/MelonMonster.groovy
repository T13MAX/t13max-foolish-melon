package behaviorTree.MelonMonster

import com.t13max.ai.behavior4j.BTNode
import com.t13max.ai.behavior4j.composites.CaseSelectorNode
import com.t13max.ai.behavior4j.leaf.ActionNode
import com.t13max.nms.ai.agent.MonsterAgent

class MelonMonster {
    Map<Integer, BTNode<MonsterAgent>> data = new HashMap<>();
    {
        data.put(0, new CaseSelectorNode1())
        data.put(2, new ActionNode2())
    }

    BTNode<MonsterAgent> getNode(int id) {
        return data.get(id)
    }

    /**
     * NODE :ActionNode
     * @Description:
     **/
    class ActionNode2 extends ActionNode<MonsterAgent> {

        @Override
        Status execute() {
            return getAgent().searchEnemy();
        }
    }

    class CaseSelectorNode1 extends CaseSelectorNode<MonsterAgent> {

        @Override
        String getCondition() {
            return getAgent().getLocalInt("hasTarget")
        }
    }

}
