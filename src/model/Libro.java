package model;

    public class Libro {
        private String isbn;
        private String titulo;
        private int autorId;
        private int anioPublicacion;
        private int cantidadTotal;
        private int cantidadDisponible;

        public Libro(String isbn, String titulo, int autorId, int anioPublicacion, int cantidadTotal, int cantidadDisponible) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autorId = autorId;
            this.anioPublicacion = anioPublicacion;
            this.cantidadTotal = cantidadTotal;
            this.cantidadDisponible = Math.min(cantidadDisponible, cantidadTotal);
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public int getAutorId() {
            return autorId;
        }

        public void setAutorId(int autorId) {
            this.autorId = autorId;
        }

        public int getAnioPublicacion() {
            return anioPublicacion;
        }

        public void setAnioPublicacion(int anioPublicacion) {
            this.anioPublicacion = anioPublicacion;
        }

        public int getCantidadTotal() {
            return cantidadTotal;
        }

        public void setCantidadTotal(int cantidadTotal) {
            this.cantidadTotal = cantidadTotal;
            if (this.cantidadDisponible > cantidadTotal) {
                this.cantidadDisponible = cantidadTotal;
            }
        }

        public int getCantidadDisponible() {
            return cantidadDisponible;
        }

        public void setCantidadDisponible(int cantidadDisponible) {
            this.cantidadDisponible = Math.min(cantidadDisponible, this.cantidadTotal);
        }
    }


