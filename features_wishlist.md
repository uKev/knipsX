Hier können fehlende/gewünschte Features festgehalten werden. 
Bitte in den entsprechenden Kategorien einsortieren und innerhalb nach Wichtigkeit sortieren.

# kleine Probleme, die vor der ersten 1.0 Beta (Abgabe Implementierungsphase) behoben werden sollten - Bugfix #
(Alias: bekannte bugs, die noch behoben werden müssen, falls neue auftreten, hier bitte eintragen)

* Bei neuer Bildmenge und ausgewähltem Ordner muss der Thread für die Thumbnailgenerierung (neu) gestartet werden (Kai/Benni)
* Internationalisierung?
* (Lower)Whisker Berechnung im Boxplot falsch
* Initialisierung des BoxplotModel am Anfang verhindern.


# Probleme, die vor dem 1.0 RC1 (Ende Validierungsphase) behoben werden sollten #
(_Nachdem_ die automatisierten Tests stehen!)

* ExifAdapter in MetaDataAdapter refactorn
* Pakethirarchy refactorn


# Probleme, die vor dem 1.0 RC2 (Abnahme) behoben werden sollten #



# Dinge, die vor dem _öffentlichen_ Release 1.0 erledigt / behoben werden müssen
* Umbenennung des Repository von tempX in knipsX
* Schreiben einer README für den Benutzer inkl. Verwendung von [Markdown](http://daringfireball.net/projects/markdown/syntax)
* Möglichst komfortable Paketierung mindestens als .exe für Windows sowie .deb für Ubuntu (Debian)
* Aufräumen des Repository
* (bebilderte) grobe Anleitung für den Benutzer mit Erklärung PictureContainer-Konzept.
* Hinzufügen eines "About"-Knopfes

# Wünsche für die erste verbesserte Version nach der Abnahme (1.1 oder 1.2) - Minor Update #

* Die Projektansicht sollte ihre Fenstergröße mit speichern und beim erneuten öffnen des Projekts wiederherstellen.


# größere Wünsche für eine kommende neue Version 1.5 oder 2.0 - Major Update #

* Bars bestehen aus Bilder die drin sind und sind durchscrollbar zum Anschauen der Bilder
* JTree soll die Bildmengenliste und Liste der Bildmengeninhalte ersetzen
* Hinzufügen einer Caching-Funktion (de-/aktivierbar irgendwo in der GUI), die Thumbnails zwischenspeichert
