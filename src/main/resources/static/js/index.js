$(function () {
    component = {
        hotLabels: $("#hotLabels"),
        carousel_indicators: $("#carousel-indicators"),
        carousel_inner: $("#carousel_inner")
    }

    function getHotLabels() {
        $.ajax(
            {
                url: '/labelInfoRest/getHotLabelInfos',
                method: 'GET',
                // dataType: "json",
                success: function (dto) {
                    let labels = dto.pos;
                    for (let i = 0; i < labels.length; i++) {
                        component.hotLabels.append(
                            "<span class=\"tag\">" +
                            labels[i]["labelName"]
                            + "</span>"
                        )
                    }
                },
                error: function (dto) {
                    console.log(dto.msg);
                }
            }
        )
    }

    function getAddPhoto() {
        $.ajax({
            url: '/addManageRest/getAll',
            method: 'GET',
            success: function (dto) {
                let adds = dto.pos;
                let carousel_inner_str, carousel_indicators_str;
                for (let i = 0; i < adds.length; i++) {
                    if (i === 0) {
                        component.carousel_indicators.append(
                            "<li data-target=\"#myCarousel\" data-slide-to=\"" + i + " \"class=\"active\"></li>"
                        )
                        component.carousel_inner.append(
                            "<div class=\"item active\"><img width=\"100%\" src=\"" + adds[i].imgUrl + "\" alt=\"First slide\"></div>"
                        )
                    } else {
                        component.carousel_indicators.append(
                            "<li data-target=\"#myCarousel\" data-slide-to=\"" + i + "\" ></li>"
                        )
                        component.carousel_inner.append(
                            "<div class=\"item\"><img width=\"100%\" src=\"" + adds[i].imgUrl + "\" alt=\"First slide\"></div>"
                        )
                    }
                }
            },
            error: function (dto) {
                console.log(dto.msg)
            }
        })
    }

    getAddPhoto();
    getHotLabels();
})