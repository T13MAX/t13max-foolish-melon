import com.t13max.ai.behavior4j.BTNode
import com.t13max.ai.behavior4j.leaf.ActionNode
import com.t13max.nms.ai.agent.PetAgent

// ---------------------------------------------------------------------
// THIS FILE IS AUTO-GENERATED BY BEHAVIAC DESIGNER, SO PLEASE DON'T MODIFY IT BY YOURSELF!
// Export file: pet/common/Task_OnBorn.groovy
// ---------------------------------------------------------------------




class Task_OnBornDfn {

    BTNode<PetAgent> getNode(int id) {
        switch (id) {
            case 59:
                return new Task_OnBorn_ActionNode59()
            default:
                return null
        }
    }


    /**
     * NODE :ActionNode
     * @Description: 
     */
    class Task_OnBorn_ActionNode59 extends ActionNode<PetAgent> {
    
        @Override
        Status execute() {
            getAgent().setLocalInt("limit", 0)
            return Status.BT_SUCCESS
        }
    }
}
