package com.simple.syscostarwarstakehomeproject.presentation.views.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.simple.syscostarwarstakehomeproject.R

@Composable
fun CardItem(
    planetName: String,
    planetClimate: String,
    planetTerrain: String,
    planetImage: String?,
    onItemClick: (() -> Unit)? = null

) {
    Card(
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp).clickable {
                onItemClick?.invoke()
        },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.dark_blue_background_color),
        ),
        border = BorderStroke(width = 2.dp, colorResource(R.color.card_stroke))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(planetImage ?: "https://picsum.photos/id/903/300/300"),
                contentDescription = "Place Holder Image",
                modifier = Modifier
                    .size(125.dp)
                    .padding(end = 10.dp, start = 15.dp, top = 25.dp, bottom = 25.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
            )
            Column {
                Text(
                    planetName,
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp, bottom = 15.dp, end = 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 20.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = colorResource(R.color.secondary_text_label))) {
                            append("Climate:")
                        }
                        append("  ")
                        withStyle(SpanStyle(color = colorResource(R.color.climate_text))) {
                            append(planetClimate)
                        }
                    },
                    modifier = Modifier.padding(bottom = 15.dp, end = 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    color = colorResource(R.color.secondary_text_label)
                )
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = colorResource(R.color.secondary_text_label))) {
                            append("Terrain:")
                        }
                        append("  ")
                        withStyle(SpanStyle(color = colorResource(R.color.green_text))) {
                            append(planetTerrain)
                        }
                    },
                    fontSize = 15.sp,
                    modifier = Modifier.padding(end = 20.dp, bottom = 10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                )
            }
        }
    }
}

@Preview
@Composable
fun CardItemPreview(modifier: Modifier = Modifier) {
    CardItem("","","","")
}