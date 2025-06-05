void main()
{
  String nombre = "Alejandro";
  int edad =19;
  final bool condicion=true;
  List<String> lista = ['Alejandro', 'Zepeda'];
  dynamic variableDinamica = "Hola mundo";

  print ('variable String: $nombre');
  print ('variable int: $edad');
  print ('variable bool: $condicion');
  print('Variable List: $lista');
  print ('Variable dynamic:$variableDinamica');
  variableDinamica=23;
  print ('Variable dynamic:$variableDinamica');
  variableDinamica=true;
  print ('Variable dynamic:$variableDinamica');
  variableDinamica=['Alejandro', 'Zepeda'];
  print ('Variable dynamic:$variableDinamica');
  variableDinamica={'nombre': 'Alejandro', 'edad': 21};
}