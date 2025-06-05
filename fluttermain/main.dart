import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget { //Siempre muestra la misma interfaz.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Sistema Escolar',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: MainPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class User {
  final String username;
  final String password;

  User({required this.username, required this.password}); //Constructores
}

class Materia {
  String name;
  String teacher;
  String day;
  String time;

  Materia({
    required this.name,
    required this.teacher, //Constructor para Materia
    required this.day,
    required this.time,
  });
}

class MainPage extends StatefulWidget { //StatefulWidget permite mantener el estado de la página
  @override
  _MainPageState createState() => _MainPageState(); //Maneja el estado de la página principal
}

class _MainPageState extends State<MainPage> { //Permite manejar el estado de MainPage.
  int _pageSeleccionada = 0; // Índice del menú seleccionado
  final List<User> _users = [];
  final List<Materia> _materias = [];
  bool _muestraUsers = false; // Controla la visibilidad de la lista de usuarios
  int? _editarMateria; // Índice de la materia que se está editando

  // Manipular el texto dentro de los TextField.
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _teacherController = TextEditingController();
  final TextEditingController _dayController = TextEditingController();
  final TextEditingController _timeController = TextEditingController();

  @override
  void dispose() { // Método para limpiar los controladores al cerrar la página
    _usernameController.dispose();
    _passwordController.dispose();
    _nameController.dispose();
    _teacherController.dispose();
    _dayController.dispose();
    _timeController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) { // Método build que construye la interfaz de usuario
    return Scaffold(
      body: Row( //Colocar el menú lateral y el contenido principal uno al lado del otro
        children: [
          // Menú Lateral
          Container(
            width: 70,
            color: Colors.grey[200],
            child: Column(
              children: [
                SizedBox(height: 30),
                IconButton(
                  icon: Icon(Icons.person, size: 30),
                  onPressed: () => setState(() => _pageSeleccionada = 0),
                ),
                SizedBox(height: 20),
                IconButton(
                  icon: Icon(Icons.book, size: 30),
                  onPressed: () => setState(() => _pageSeleccionada = 1),
                ),
                Spacer(),
              ],
            ),
          ),
          // Contenido principal
          Expanded(
            child: _pageSeleccionada == 0 ? _buildLoginPage() : _buildMateriasPage(), // Muestra la página de login o materias según el índice seleccionado
          ),
        ],
      ),
    );
  }

  Widget _buildLoginPage() {
    return Container(
      padding: EdgeInsets.all(20),
      child: SingleChildScrollView( // Permite el desplazamiento si el contenido es demasiado grande
        child: Column(
          children: [
            SizedBox(height: 30),
            Text('Login', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold)),
            SizedBox(height: 30),
            Column(
              children: [
                Text('Nombre de usuario'),
                TextField(controller: _usernameController),
                SizedBox(height: 20),
                Text('Contraseña'),
                TextField(controller: _passwordController, obscureText: true),
                SizedBox(height: 25),
                ElevatedButton(
                  onPressed: _registerUser,
                  child: Text('REGISTRAR USUARIO'),
                ),
                SizedBox(height: 15),
                ElevatedButton(
                  onPressed: () => setState(() => _muestraUsers = !_muestraUsers), // Alterna la visibilidad de la lista de usuarios
                  child: Text(_muestraUsers ? 'OCULTAR USUARIOS' : 'MOSTRAR USUARIOS'),
                ),
              ],
            ),
            if (_muestraUsers) _buildUsersList(), // Muestra la lista de usuarios si _showUsers es true
            SizedBox(height: 20),
          ],
        ),
      ),
    );
  }

  Widget _buildUsersList() { 
    return Column(
      children: [
        Text('Usuarios Registrados', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
        SizedBox(height: 10),
        if (_users.isEmpty)
          Text('No hay usuarios registrados')
        else
          ..._users.map((user) => ListTile( //Recorre la lista de usuarios y crea un ListTile para cada uno al editar o eliminar usuarios
            title: Text(user.username),
            trailing: IconButton(
              icon: Icon(Icons.delete, color: Colors.red),
              onPressed: () => setState(() => _users.remove(user)), // Elimina el usuario de la lista
            ),
          )).toList(),
      ],
    );
  }

  Widget _buildMateriasPage() {
    return Padding(
      padding: EdgeInsets.all(16),
      child: SingleChildScrollView( 
        child: Column(
          children: [
            SizedBox(height: 30),
            Text('Materias Escolares', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold)),
            SizedBox(height: 30),
            Column(
              children: [
                Text('Nombre de la materia'),
                TextField(controller: _nameController),
                SizedBox(height: 20),
                Text('Nombre del maestro'),
                TextField(controller: _teacherController),
                SizedBox(height: 20),
                Text('Día de la clase'),
                TextField(controller: _dayController),
                SizedBox(height: 20),
                Text('Hora de la clase'),
                TextField(controller: _timeController),
                SizedBox(height: 20),
                Row(
                  children: [
                    Expanded(
                      child: ElevatedButton(
                        onPressed: _addMateria,
                        child: Text('DAR DE ALTA'),
                      ),
                    ),
                    SizedBox(width: 10),
                    Expanded(
                      child: ElevatedButton(
                        onPressed: _editarMateria != null ? _saveMateria : null, //Una materia en edicion puede ser guardada
                        child: Text('GUARDAR'),
                      ),
                    ),
                  ],
                ),
              ],
            ),
            SizedBox(height: 25),
            ..._materias.asMap().entries.map((entry) => _buildMateriaItem(entry.key, entry.value)).toList(), // Muestra la lista de materias
            // Utiliza asMap().entries para obtener el índice y el objeto Materia
            SizedBox(height: 20),
          ],
        ),
      ),
    );
  }

  Widget _buildMateriaItem(int index, Materia materia) //Index y Materia como parámetros
  //Index representa la posición de la materia en la lista _materias
   {
    return Container(
      margin: EdgeInsets.symmetric(vertical: 5),
      child: Row(
        children: [
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start, // Alinea el texto a la izquierda
              children: [
                if (_editarMateria == index) ...[ // Si la materia está en edición, muestra los campos de texto
                  Text('Nombre'),
                  TextField(controller: _nameController..text = materia.name),
                  Text('Maestro'),
                  TextField(controller: _teacherController..text = materia.teacher),
                  Text('Día'),
                  TextField(controller: _dayController..text = materia.day),
                  Text('Hora'),
                  TextField(controller: _timeController..text = materia.time),
                ] else ...[
                  Text(materia.name, style: TextStyle(fontWeight: FontWeight.bold)),
                  Text('Maestro: ${materia.teacher}'),
                  Text('Día: ${materia.day}'),
                  Text('Hora: ${materia.time}'),
                ],
              ],
            ),
          ),
          IconButton(
            icon: Icon(Icons.edit, color: Colors.blue),
            onPressed: () => _startEditing(index, materia), // Inicia la edición de la materia
          ),
          IconButton(
            icon: Icon(Icons.delete, color: Colors.red),
            onPressed: () => _deleteMateria(index), // Elimina la materia de la lista
          ),
        ],
      ),
    );
  }

  void _registerUser() { // Método para registrar un nuevo usuario
    // Verifica que el campo de nombre de usuario no esté vacío antes de agregarlo
    if (_usernameController.text.isNotEmpty) {
      setState(() {
        _users.add(User(
          username: _usernameController.text,
          password: _passwordController.text,
        ));
        _usernameController.clear();
        _passwordController.clear();
      });
    }
  }

  void _addMateria() { // Método para agregar una nueva materia
    if (_nameController.text.isNotEmpty) {
      setState(() {
        _materias.add(Materia(
          name: _nameController.text,
          teacher: _teacherController.text,
          day: _dayController.text,
          time: _timeController.text,
        ));
        _clearMateriaControllers();
      });
    }
  }

  void _startEditing(int index, Materia materia) { // Método para iniciar la edición de una materia
    setState(() {
      _editarMateria = index;
      _nameController.text = materia.name;
      _teacherController.text = materia.teacher;
      _dayController.text = materia.day;
      _timeController.text = materia.time;
    });
  }

  void _saveMateria() { // Método para guardar los cambios de una materia editada
    if (_editarMateria != null) {
      setState(() {
        _materias[_editarMateria!] = Materia(
          name: _nameController.text,
          teacher: _teacherController.text,
          day: _dayController.text,
          time: _timeController.text,
        );
        _editarMateria = null;
        _clearMateriaControllers();
      });
    }
  }

  void _deleteMateria(int index) { // Método para eliminar una materia de la lista
    setState(() {
      _materias.removeAt(index);
      if (_editarMateria == index) {
        _editarMateria = null;
        _clearMateriaControllers();
      }
    });
  }

  void _clearMateriaControllers() { // Método para limpiar los controladores de texto de las materias
    _nameController.clear();
    _teacherController.clear();
    _dayController.clear();
    _timeController.clear();
  }
}