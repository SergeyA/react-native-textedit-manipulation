
# react-native-textedit-manipulation

## Getting started

`$ npm install react-native-textedit-manipulation --save`

### Mostly automatic installation

`$ react-native link react-native-textedit-manipulation`

## Usage

Register a component as custom keyboard: 

```javascript
import React, { Component } from 'react';
import {
  TouchableOpacity,
  Text,
  View,
  findNodeHandle,
} from 'react-native';
import { insertText } from 'react-native-textedit-manipulation';

insertText(findNodeHandle(editRef.current, "Hello, world"));

```

## API

### insertText(tag, text)

Insert text to `TextInput` as cursor position.

### backSpace(tag)

Delete selected text or the charactor before cursor.

### doDelete(tag)

Delete selected text or the charactor after cursor.

### moveLeft(tag)

Move cursor to selection start or move cursor left.

### MoveRight(tag)

Move cursor to selection end or move cursor right.
