class AlbumsModel {
  int id;
  String title;
  int userId;

  AlbumsModel.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        title = json['title'],
        userId = json['userId'];
}
