void main(List<String> arg)
{
  // Crear un objeto de la clase Alumno
  Alumno alumno1 = Alumno('Alejandro', 21, 'Ingeniería', 9.5);
  
  // Crear otro objeto de la clase Alumno
  Alumno alumno2 = Alumno('Juan', 20, 'Medicina', 8.7);
  
  print(alumno2.nombre);
}

class Alumno
{
  String nombre;
  int edad;
  String carrera;
  double promedio;

  Alumno(this.nombre, this.edad, this.carrera, this.promedio);

  void mostrarInformacion()
  {
  print('Nombre: $nombre');
  print('Edad: $edad');
  print('Carrera: $carrera');
  print('Promedio: $promedio');
  }
  @override
  String toString()
  {
    return 'Nombre: $nombre, Edad: $edad, Carrera: $carrera, Promedio: $promedio';
  }
}