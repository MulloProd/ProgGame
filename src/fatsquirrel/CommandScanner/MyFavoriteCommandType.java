package fatsquirrel.CommandScanner;

//beispiel implementation vom interface, wird später gelöscht
public enum MyFavoriteCommandType implements CommandTypeInfo {

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
            return "exit program";
        }

        @Override
        public Class[] getParamTypes() {
            return new Class[0];
        }
    },
    ADDI {
        @Override
        public String getName() {
            return "addi";
        }

        @Override
        public String getHelpText() {
            return "simple integer add";
        }

        @Override
        public Class[] getParamTypes() {
            Class[] temp = new Class[2];
            temp[0] = int.class;
            temp[1] = int.class;
            return temp;
        }
    },
    ADDF {
        @Override
        public String getName() {
            return "addf";
        }

        @Override
        public String getHelpText() {
            return "simple float add";
        }

        @Override
        public Class[] getParamTypes() {
            Class[] temp = new Class[2];
            temp[0] = float.class;
            temp[1] = float.class;
            return temp;
        }
    },
    ECHO {
        @Override
        public String getName() {
            return "echo";
        }

        @Override
        public String getHelpText() {
            return "echos param1 string param2 times";
        }

        @Override
        public Class[] getParamTypes() {
            Class[] temp = new Class[2];
            temp[0] = String.class;
            temp[1] = int.class;
            return temp;
        }
    }
}
