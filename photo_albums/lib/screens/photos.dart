import 'package:flutter/material.dart';
import 'package:photo_albums/core/app_text_styles.dart';
import 'package:photo_albums/models/photos_model.dart';
import 'package:photo_albums/service/placeholder_service.dart';

class Photos extends StatelessWidget {
  final int albumId;
  final _placeHolderService = PlaceHolderService();

  Photos(this.albumId);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.purple,
        title: Text("Fotos"),
      ),
      body: Container(
        color: Colors.white70,
        child: FutureBuilder<List<PhotosModel>>(
          future: _placeHolderService.getPhotos(albumId),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return ListView.builder(
                padding: EdgeInsets.all(8),
                itemCount: snapshot.data.length,
                itemBuilder: (context, index) {
                  return Card(
                    child: Row(
                      children: [
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Expanded(
                            flex: 1,
                            child: Container(
                              width: 58,
                              height: 58,
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(10),
                                image: DecorationImage(
                                  image: NetworkImage(
                                      '${snapshot.data[index].thumbnailUrl}'),
                                ),
                              ),
                            ),
                          ),
                        ),
                        Expanded(
                            flex: 3,
                            child: Text('${snapshot.data[index].title}',
                                style: AppTextStyles.titleBold))
                      ],
                    ),
                  );
                },
              );
            } else if (snapshot.hasError) {
              return Text("erro");
            } else {
              return Center(child: CircularProgressIndicator());
            }
          },
        ),
      ),
    );
  }
}
