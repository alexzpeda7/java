void main(List<String> arg){
  Map<String, String> mapa ={
    'nombre': 'Alejandro',
    'apellido':'Zepeda',
    'edad':'19'
  };
  print ('Mapa: $mapa');
  print ('Nombre: ${mapa['nombre']}');

  mapa['nombre']='Juan';
  print ('Mapa: $mapa');

  mapa['domicilio']='calle 23';
  print('Mapa: $mapa');
}