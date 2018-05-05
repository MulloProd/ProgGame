package fatsquirrel.Console;

public enum GameCommandType implements CommandTypeInfo {

    HELP {
        @Override
        public String getName() {
            return "help";
        }

        @Override
        public String getHelpText() {
            return "list all commands";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    EXIT {
        @Override
        public String getName() {
            return "exit";
        }

        @Override
        public String getHelpText() {
            return "Exit the Game.";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    ALL {
        @Override
        public String getName() {
            return "all";
        }

        @Override
        public String getHelpText() {
            return "Shows all entities with their energies";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    LEFT {
        @Override
        public String getName() {
            return "left";
        }

        @Override
        public String getHelpText() {
            return "Mastersquirrel takes one step left";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    UP {
        @Override
        public String getName() {
            return "up";
        }

        @Override
        public String getHelpText() {
            return "Mastersquirrel takes one step up";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    DOWN {
        @Override
        public String getName() {
            return "down";
        }

        @Override
        public String getHelpText() {
            return "Mastersquirrel takes one step down";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    RIGHT {
        @Override
        public String getName() {
            return "right";
        }

        @Override
        public String getHelpText() {
            return "Mastersquirrel takes one step right";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    MASTER_ENERGY {
        @Override
        public String getName() {
            return "master_energy";
        }

        @Override
        public String getHelpText() {
            return "<mastersquirrel>, shows how much energy the Mastersquirrel has left";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[]{String.class};
        }
    },
    SPAWN_MINI {
        @Override
        public String getName() {
            return "spawn_mini";
        }

        @Override
        public String getHelpText() {
            return "<int>, Startenergy of minisquirrel";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[]{int.class};
        }
    }

}
