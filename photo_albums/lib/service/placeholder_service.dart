import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:photo_albums/models/albums_model.dart';
import 'package:photo_albums/models/photos_model.dart';

class PlaceHolderService {
  final baseUrl = 'https://jsonplaceholder.typicode.com';

  Future<List<AlbumsModel>> getAlbums() async {
    var response = await http.get("$baseUrl/albums");

    if (response.statusCode == 200) {
      var objs = jsonDecode(response.body) as List;
      var albums = objs.map((obj) => AlbumsModel.fromJson(obj)).toList();
      return albums;
    } else {
      throw Exception('Erro ao carregar álbuns');
    }
  }

  Future<List<PhotosModel>> getPhotos(albumId) async {
    var response = await http.get("$baseUrl/albums/$albumId/photos");

    if (response.statusCode == 200) {
      var objs = jsonDecode(response.body) as List;
      var photos = objs.map((obj) => PhotosModel.fromJson(obj)).toList();
      return photos;
    } else {
      throw Exception('Erro ao carregar fotos');
    }
  }
}
