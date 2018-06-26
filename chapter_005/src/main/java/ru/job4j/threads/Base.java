package ru.job4j.threads;

public class Base {
        private int id;
        private String name;
        private int version = 0;

        public Base(int id, String name) {
            this.id = id;
            this.name = name;
        }


        int getId() {
            return id;
        }


        public String getName() {
            return name;
        }


        int getVersion() {
            return version;
        }


        void update() {
            this.version++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Base model = (Base) o;

            if (id != model.id) {
                return false;
            }
            if (version != model.version) {
                return false;
            }
            return name.equals(model.name);
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + name.hashCode();
            result = 31 * result + version;
            return result;
        }
}
