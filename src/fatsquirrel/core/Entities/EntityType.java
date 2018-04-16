package fatsquirrel.core.Entities;

public class EntityType {
    public enum Types{Wall,BadPlant,GoodPlant,BadBeast,GoodBeast, MasterSquirrel,MiniSquirrel,None}
    private Types type;

    public EntityType(Class c){
        if(c!=null) {
            switch (c.getSimpleName()) {
                case "Wall":
                    type = Types.Wall;
                    break;
                case "BadPlant":
                    type = Types.BadPlant;
                    break;
                case "GoodPlant":
                    type = Types.GoodPlant;
                    break;
                case "BadBeast":
                    type = Types.BadBeast;
                    break;
                case "GoodBeast":
                    type = Types.GoodBeast;
                    break;
                case "MasterSquirrel":
                    type = Types.MasterSquirrel;
                    break;
                case "MiniSquirrel":
                    type = Types.MiniSquirrel;
                    break;
            }
        }
        else
            type = Types.None;
    }

    public Types getType() {
        return type;
    }
}
