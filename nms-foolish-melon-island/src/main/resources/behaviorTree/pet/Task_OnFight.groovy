import com.t13max.ai.behavior4j.BTNode
import com.t13max.ai.behavior4j.attachments.PreActionNode
import com.t13max.ai.behavior4j.composites.CaseSelectorNode
import com.t13max.ai.behavior4j.leaf.ActionNode
import com.t13max.ai.behavior4j.leaf.AssignmentNode
import com.t13max.ai.behavior4j.leaf.ConditionNode
import com.t13max.nms.ai.agent.PetAgent

// ---------------------------------------------------------------------
// THIS FILE IS AUTO-GENERATED BY BEHAVIAC DESIGNER, SO PLEASE DON'T MODIFY IT BY YOURSELF!
// Export file: pet/common/Task_OnFight.groovy
// ---------------------------------------------------------------------

class Task_OnFightDfn {

    BTNode<PetAgent> getNode(int id) {
        switch (id) {
            case 21:
                return new Task_OnFight_CaseSelectorNode21()
            case 57:
                return new Task_OnFight_PreActionNode57()
            case 58:
                return new Task_OnFight_ActionNode58()
            case 59:
                return new Task_OnFight_ActionNode59()
            case 49:
                return new Task_OnFight_ConditionNode49()
            case 50:
                return new Task_OnFight_ConditionNode50()
            case 51:
                return new Task_OnFight_ConditionNode51()
            case 52:
                return new Task_OnFight_ConditionNode52()
            case 53:
                return new Task_OnFight_ConditionNode53()
            case 54:
                return new Task_OnFight_ActionNode54()
            case 84:
                return new Task_OnFight_CaseSelectorNode84()
            case 46:
                return new Task_OnFight_ActionNode46()
            case 60:
                return new Task_OnFight_ActionNode60()
            case 61:
                return new Task_OnFight_ActionNode61()
            case 62:
                return new Task_OnFight_ActionNode62()
            case 87:
                return new Task_OnFight_ActionNode87()
            case 88:
                return new Task_OnFight_ActionNode88()
            case 75:
                return new Task_OnFight_PreActionNode75()
            case 76:
                return new Task_OnFight_ActionNode76()
            case 77:
                return new Task_OnFight_ActionNode77()
            case 34:
                return new Task_OnFight_ConditionNode34()
            case 35:
                return new Task_OnFight_ConditionNode35()
            case 36:
                return new Task_OnFight_ConditionNode36()
            case 37:
                return new Task_OnFight_ConditionNode37()
            case 5:
                return new Task_OnFight_ConditionNode5()
            case 32:
                return new Task_OnFight_ActionNode32()
            case 27:
                return new Task_OnFight_ActionNode27()
            case 14:
                return new Task_OnFight_PreActionNode14()
            case 30:
                return new Task_OnFight_AssignmentNode30()
            case 12:
                return new Task_OnFight_ActionNode12()
            case 17:
                return new Task_OnFight_PreActionNode17()
            case 16:
                return new Task_OnFight_AssignmentNode16()
            case 19:
                return new Task_OnFight_ActionNode19()
            case 13:
                return new Task_OnFight_PreActionNode13()
            case 15:
                return new Task_OnFight_PreActionNode15()
            case 39:
                return new Task_OnFight_ActionNode39()
            case 20:
                return new Task_OnFight_ActionNode20()
            case 3:
                return new Task_OnFight_ActionNode3()
            case 23:
                return new Task_OnFight_ActionNode23()
            case 80:
                return new Task_OnFight_PreActionNode80()
            case 81:
                return new Task_OnFight_ActionNode81()
            case 82:
                return new Task_OnFight_ActionNode82()
            case 67:
                return new Task_OnFight_ConditionNode67()
            case 68:
                return new Task_OnFight_ConditionNode68()
            case 69:
                return new Task_OnFight_ConditionNode69()
            case 70:
                return new Task_OnFight_ConditionNode70()
            case 71:
                return new Task_OnFight_ConditionNode71()
            case 72:
                return new Task_OnFight_ActionNode72()
            case 41:
                return new Task_OnFight_ConditionNode41()
            case 42:
                return new Task_OnFight_ActionNode42()
            case 43:
                return new Task_OnFight_ActionNode43()
            default:
                return null
        }
    }


    /**
     * NODE :CaseSelectorNode
     * @Description: 
     */
    class Task_OnFight_CaseSelectorNode21 extends CaseSelectorNode<PetAgent> {
    
        @Override
        String getCondition() {
            return getAgent().getPetMode()
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnFight_PreActionNode57 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getLocalInt("limit") == 2
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode58 extends ActionNode<PetAgent> {
    
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
    class Task_OnFight_ActionNode59 extends ActionNode<PetAgent> {
    
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
    class Task_OnFight_ConditionNode49 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerDeath()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode50 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerLeave()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode51 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerLeaveScene()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode52 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerOutRange(15)
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode53 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isTargetDead()
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
    class Task_OnFight_ActionNode54 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().enterIdle()
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :CaseSelectorNode
     * @Description: 
     */
    class Task_OnFight_CaseSelectorNode84 extends CaseSelectorNode<PetAgent> {
    
        @Override
        String getCondition() {
            return getAgent().getPetMode()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode46 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().chooseSkillIfAbsent()
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode60 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().chaseTarget()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode61 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().stopMotion()
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode62 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().attackTarget()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode87 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().enterIdle()
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode88 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().enterIdle()
            return Status.BT_SUCCESS
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnFight_PreActionNode75 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getLocalInt("limit") == 2
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode76 extends ActionNode<PetAgent> {
    
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
    class Task_OnFight_ActionNode77 extends ActionNode<PetAgent> {
    
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
    class Task_OnFight_ConditionNode34 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerDeath()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode35 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerLeave()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode36 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerLeaveScene()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode37 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerOutRange(15)
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode5 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isTargetDead()
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
    class Task_OnFight_ActionNode32 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().enterIdle()
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode27 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().weightSelectSkill(10000)
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnFight_PreActionNode14 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getMagicType() == 10
        }
    }

    /**
     * NODE :AssignmentNode
     * @Description: 
     */
    class Task_OnFight_AssignmentNode30 extends AssignmentNode<PetAgent> {
    
        @Override
        void assignment() {
            getAgent().tempLong1 = getAgent().getOwnerId()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode12 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().followAndAttackFriend(getAgent().tempLong1, 15)
            return Status.BT_SUCCESS
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnFight_PreActionNode17 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getMagicType() == 11
        }
    }

    /**
     * NODE :AssignmentNode
     * @Description: 
     */
    class Task_OnFight_AssignmentNode16 extends AssignmentNode<PetAgent> {
    
        @Override
        void assignment() {
            getAgent().tempLong1 = getAgent().getObjId()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode19 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().followAndAttackFriend(getAgent().tempLong1, 1)
            return Status.BT_SUCCESS
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnFight_PreActionNode13 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getMagicType() != 10
        }
    }
    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnFight_PreActionNode15 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getMagicType() != 11
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode39 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().changeOwnerTargetId()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode20 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().chaseTarget()
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode3 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().stopMotion()
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode23 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().attackTarget()
        }
    }

    /**
     * ATTACHMENT :PreActionNode
     */
    class Task_OnFight_PreActionNode80 extends PreActionNode<PetAgent> {
    
        @Override
        boolean preCondition() {
            return getAgent().getLocalInt("limit") != 2
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnFight_ActionNode81 extends ActionNode<PetAgent> {
    
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
    class Task_OnFight_ActionNode82 extends ActionNode<PetAgent> {
    
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
    class Task_OnFight_ConditionNode67 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerDeath()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode68 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerLeave()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode69 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerLeaveScene()
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode70 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isOwnerOutRange(15)
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
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode71 extends ConditionNode<PetAgent> {
    
        Object operatorLeft() {
            return getAgent().isTargetDead()
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
    class Task_OnFight_ActionNode72 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().enterIdle()
            return Status.BT_SUCCESS
        }
    }

    /**
     * NODE :ConditionNode
     * @Description: 
     */
    class Task_OnFight_ConditionNode41 extends ConditionNode<PetAgent> {
    
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
    class Task_OnFight_ActionNode42 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            return getAgent().transferOwnerPos(2)
        }
    }

    /**
     * NODE :ActionNode
     * @Description: 按配置移动
     */
    class Task_OnFight_ActionNode43 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().moveFollowOwner(5, 4, 35)
            return Status.BT_SUCCESS
        }
    }
}
