# PROBLEM -(1948) Delete Duplicate Folders in System

# SOLUTION :-

class Solution(object):
    def deleteDuplicateFolder(self, paths):
        """
        :type paths: List[List[str]]
        :rtype: List[List[str]]
        """
        from collections import defaultdict

        # Step 1: Build the Trie structure
        root = {}

        for path in paths:
            cur = root
            for folder in path:
                if folder not in cur:
                    cur[folder] = {}
                cur = cur[folder]

        # Step 2: Serialize subtrees and group by signature
        groups = defaultdict(list)

        def serialize(node):
            if not node:
                return ""
            children_serial = []
            for name in sorted(node.keys()):
                sub_serial = name + "(" + serialize(node[name]) + ")"
                children_serial.append(sub_serial)
            serial = "".join(children_serial)
            if serial:
                groups[serial].append(node)
            return serial

        serialize(root)

        # Step 3: Mark duplicates
        duplicates = set()
        for serial, nodes in groups.items():
            if len(nodes) > 1:
                for node in nodes:
                    duplicates.add(id(node))

        # Step 4: DFS to collect remaining paths
        res = []

        def dfs(node, path):
            for name, child in node.items():
                if id(child) not in duplicates:
                    new_path = path + [name]
                    res.append(new_path)
                    dfs(child, new_path)

        dfs(root, [])
        return res
