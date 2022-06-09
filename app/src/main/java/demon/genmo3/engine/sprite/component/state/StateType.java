package demon.genmo3.engine.sprite.component.state;

public enum StateType
{
        IDLE("IDLE"),
                JUMPING("JUMPING"),
                FLOATING("FLOATING"),
                DEATH("DEATH"),
                RUN("RUN"),
                ATTACK("ATTACK"),
                ATTACKED("ATTACKED"),
                SKILL1("SKILL1"),
                SKILL2("SKILL2"),
                SKILL3("SKILL3"),
                SKILL4("SKILL4"),
                ITEM("ITEM");

        private final String state;

        StateType(String state)
        {
            this.state = state;
        }

        public String getState()
        {
            return state;
        }
}
