import 'package:flutter/material.dart';
import 'package:photo_albums/core/app_text_styles.dart';
import 'package:photo_albums/models/albums_model.dart';
import 'package:photo_albums/service/placeholder_service.dart';

import 'photos.dart';

class Albums extends StatefulWidget {
  @override
  _AlbumsState createState() => _AlbumsState();
}

class _AlbumsState extends State<Albums> {
  final PlaceHolderService _placeHolderService = PlaceHolderService();


  @override
  void initState() {
    super.initState();

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.purple,
          title: Text("Álbuns de Fotos"),
        ),
        body: Container(
          color: Colors.white70,
          child: FutureBuilder<List<AlbumsModel>>(
            future: _placeHolderService.getAlbums(),
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return ListView.builder(
                    itemCount: snapshot.data.length,
                    itemBuilder: (context, index) {
                      return GestureDetector(
                        onTap: () {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) =>
                                      Photos(snapshot.data[index].id)));
                        },
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Card(
                            child: Column(
                              children: [
                                ListTile(
                                  title: Text('${snapshot.data[index].title}', style: AppTextStyles.titleBold,),
                                  subtitle: Text('id: ${snapshot.data[index].id} | userid: ${snapshot.data[index].userId}', style: AppTextStyles.title),
                                )
                              ],
                            ),
                          ),
                        ),
                      );
                    });
              } else if (snapshot.hasError) {
                return Text("erro");
              } else {
                return Center(child: CircularProgressIndicator());
              }
            },
          ),
        ));
  }
}
