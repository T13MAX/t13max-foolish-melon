import com.t13max.ai.behavior4j.BTNode
import com.t13max.ai.behavior4j.attachments.PreActionNode
import com.t13max.ai.behavior4j.composites.CaseSelectorNode
import com.t13max.ai.behavior4j.leaf.ActionNode
import com.t13max.ai.behavior4j.leaf.AssignmentNode
import com.t13max.ai.behavior4j.leaf.ConditionNode
import com.t13max.nms.ai.agent.PetAgent

// ---------------------------------------------------------------------
// THIS FILE IS AUTO-GENERATED BY BEHAVIAC DESIGNER, SO PLEASE DON'T MODIFY IT BY YOURSELF!
// Export file: pet/common/Task_OnIdle.groovy
// ---------------------------------------------------------------------

class Task_OnIdleDfn {

    BTNode<PetAgent> getNode(int id) {
        switch (id) {
            case 11:
                return new Task_OnIdle_CaseSelectorNode11()
            case 47:
                return new Task_OnIdle_PreActionNode47()
            case 48:
                return new Task_OnIdle_ActionNode48()
            case 49:
                return new Task_OnIdle_ActionNode49()
            case 31:
                return new Task_OnIdle_ConditionNode31()
            case 32:
                return new Task_OnIdle_ActionNode32()
            case 33:
                return new Task_OnIdle_ActionNode33()
            case 41:
                return new Task_OnIdle_ActionNode41()
            case 42:
                return new Task_OnIdle_PreActionNode42()
            case 43:
                return new Task_OnIdle_ActionNode43()
            case 44:
                return new Task_OnIdle_ActionNode44()
            case 8:
                return new Task_OnIdle_ConditionNode8()
            case 9:
                return new Task_OnIdle_ActionNode9()
            case 2:
                return new Task_OnIdle_ActionNode2()
            case 14:
                return new Task_OnIdle_ConditionNode14()
            case 13:
                return new Task_OnIdle_AssignmentNode13()
            case 4:
                return new Task_OnIdle_ActionNode4()
            case 5:
                return new Task_OnIdle_PreActionNode5()
            case 80:
                return new Task_OnIdle_PreActionNode80()
            case 81:
                return new Task_OnIdle_ActionNode81()
            case 82:
                return new Task_OnIdle_ActionNode82()
            case 19:
                return new Task_OnIdle_ConditionNode19()
            case 20:
                return new Task_OnIdle_ActionNode20()
            case 21:
                return new Task_OnIdle_ActionNode21()
            case 23:
                return new Task_OnIdle_ConditionNode23()
            case 24:
                return new Task_OnIdle_AssignmentNode24()
            case 25:
                return new Task_OnIdle_ActionNode25()
            case 26:
                return new Task_OnIdle_PreActionNode26()
            default:
                return null
        }
    }


    /**
     * NODE :CaseSelectorNode
     * @Description: 
     */
    class Task_OnIdle_CaseSelectorNode11 extends CaseSelectorNode<PetAgent> {
    
        @Override
        String getCondition() {
            return getAgent().getPetMode()
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnIdle_PreActionNode47 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getLocalInt("limit") == 2
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode48 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().delAura(51)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode49 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().setLocalInt("limit", 0)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnIdle_ConditionNode31 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerOutRange(25)
        }

        Object operatorRight() {
            return true
        }

        @Override
        boolean condition() {
            return operatorLeft() == operatorRight()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode32 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().transferOwnerPos(2)
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 按配置移动
     */
    class Task_OnIdle_ActionNode33 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().moveFollowOwner(5, 4, 35)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode41 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().searchEnemy()
            return Status.BT_SUCCESS
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnIdle_PreActionNode42 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getLocalInt("limit") == 2
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode43 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().delAura(51)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode44 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().setLocalInt("limit", 1)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnIdle_ConditionNode8 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerOutRange(25)
        }

        Object operatorRight() {
            return true
        }

        @Override
        boolean condition() {
            return operatorLeft() == operatorRight()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode9 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().transferOwnerPos(2)
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 按配置移动
     */
    class Task_OnIdle_ActionNode2 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().moveFollowOwner(5, 4, 35)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnIdle_ConditionNode14 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerInFight()
        }

        Object operatorRight() {
            return true
        }

        @Override
        boolean condition() {
            return operatorLeft() == operatorRight()
        }
    }

    /**
     * NODE :AssignmentNode
     * @Description: 
     */
    class Task_OnIdle_AssignmentNode13 extends AssignmentNode<PetAgent> {
    
        @Override
        void assignment() {
            getAgent().tempLong1 = getAgent().getOwnerId()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode4 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().changeOwnerTargetId()
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnIdle_PreActionNode5 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getDistance(getAgent().tempLong1) <= 12
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnIdle_PreActionNode80 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getLocalInt("limit") != 2
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode81 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().addAura(51)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode82 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().setLocalInt("limit", 2)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnIdle_ConditionNode19 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerOutRange(25)
        }

        Object operatorRight() {
            return true
        }

        @Override
        boolean condition() {
            return operatorLeft() == operatorRight()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode20 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().transferOwnerPos(2)
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 按配置移动
     */
    class Task_OnIdle_ActionNode21 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().moveFollowOwner(5, 4, 35)
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnIdle_ConditionNode23 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerInFight()
        }

        Object operatorRight() {
            return true
        }

        @Override
        boolean condition() {
            return operatorLeft() == operatorRight()
        }
    }

    /**
     * NODE :AssignmentNode
     * @Description: 
     */
    class Task_OnIdle_AssignmentNode24 extends AssignmentNode<PetAgent> {
    
        @Override
        void assignment() {
            getAgent().tempLong1 = getAgent().getOwnerId()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnIdle_ActionNode25 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().changeOwnerTargetId()
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnIdle_PreActionNode26 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getDistance(getAgent().tempLong1) <= 12
        }
    }
}
