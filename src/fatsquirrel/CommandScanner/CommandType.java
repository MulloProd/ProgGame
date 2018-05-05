package fatsquirrel.CommandScanner;

//beispiel implementation vom interface, wird später gelöscht
public enum CommandType implements CommandTypeInfo {

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
            return new Class[]{int.class, int.class};
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
            return new Class[]{float.class, float.class};
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
            return new Class[]{String.class, int.class};
        }
    }
}
