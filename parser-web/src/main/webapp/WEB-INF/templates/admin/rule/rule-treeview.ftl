<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>通用解析</title>
</head>

<style>

    .node circle {
        fill: #fff;
        stroke: steelblue;
        stroke-width: 1.5px;
    }

    .node {
        font: 10px sans-serif;
    }

    .link {
        fill: none;
        stroke: #ccc;
        stroke-width: 1.5px;
    }

</style>

<body>
<script src="/admin/static/js/d3.min.js"></script>
<script>
    var QUERYSTRING_PATTERN_PREFIX = '[\\?\\&\\#]';
    var QUERYSTRING_PATTERN_SUFFIX = '=([^&]*)';

    var QueryString = {};

    QueryString.get = function (key, string) {
        string = string || window.location.search;
        var matches = string.match(new RegExp(QUERYSTRING_PATTERN_PREFIX + key + QUERYSTRING_PATTERN_SUFFIX, 'i'));
        var encodedValue = matches && matches[1];
        var value = encodedValue && decodeURIComponent(encodedValue);
        return value;
    };

    var width = 960;
    var height = 1360;

    var cluster = d3.layout.cluster()
            .size([height, width - 160]);

    var diagonal = d3.svg.diagonal()
            .projection(function (d) {
                return [d.y, d.x];
            });

    var svg = d3.select("body").append("svg")
            .attr("width", width)
            .attr("height", height)
            .append("g")
            .attr("transform", "translate(40,0)");

    d3.json("http://" + window.location.host + "/admin/rule/" + QueryString.get("ruleId") + "/treeview", function (error, root) {
        var nodes = cluster.nodes(root),
                links = cluster.links(nodes);

        var link = svg.selectAll(".link")
                .data(links)
                .enter().append("path")
                .attr("class", "link")
                .attr("d", diagonal);

        var node = svg.selectAll(".node")
                .data(nodes)
                .enter().append("g")
                .attr("class", "node")
                .attr("transform", function (d) {
                    return "translate(" + d.y + "," + d.x + ")";
                })

        node.append("circle")
                .attr("r", 4.5);

        node.append("text")
                .attr("dx", function (d) {
                    return d.children ? -8 : 8;
                })
                .attr("dy", 3)
                .style("text-anchor", function (d) {
                    return d.children ? "end" : "start";
                })
                .text(function (d) {
                    return d.name;
                })
                .style("font-size","14px");
    });

    d3.select(self.frameElement).style("height", height + "px");

</script>
