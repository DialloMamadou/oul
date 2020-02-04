package gestiondocuments;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import modele.Client;
import modele.Sejour;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

public class GestionDocsImpl implements GestionDocs {
    public GestionDocsImpl(){ }
    @Override
    public void genereAttestationFacture(Client client, Sejour sejour) {
        Document doc = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/main/resources/docs/Inscription_"+client.prenom_client.get()+".pdf"));
            doc.open();

            //Add Image
            Image img = Image.getInstance("src/main/resources/img/oul.jpg");
            //Fixed Positioning
            img.setAbsolutePosition(25f, 750f);
            //Scale to new height and new width of image
            img.scaleAbsolute(80, 80);
            //Add to document
            doc.add(img);
            //doc.add(Chunk.SPACETABBING);
            doc.add(new Paragraph("\n\n"));
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
            font.setColor(BaseColor.BLUE);
            Font font1 =FontFactory.getFont(FontFactory.HELVETICA, 10);
            font1.setColor(BaseColor.BLUE);

            Font font0 =FontFactory.getFont(FontFactory.HELVETICA, 8);

            Font font2 =FontFactory.getFont(FontFactory.HELVETICA, 10);


            doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n",font));
            doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n",font0));
            doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n",font1));
            doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E",font0));

            DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);
            Paragraph jour = new Paragraph("Le "+fullDateFormat.format(new Date()),font2);
            jour.setAlignment(Element.ALIGN_CENTER);
            doc.add(jour);

            Paragraph dest = new Paragraph("Mme ou M. "+client.observation.get()+"\n"+client.codePostale.get()+" "+client.adresse.get(),font2);
            dest.setAlignment(Element.ALIGN_RIGHT);
            doc.add(dest);

            doc.add(new Paragraph("Référence à rappeler :( 159297 / 1112 - 8V0)",font2));
            Image alerte = Image.getInstance("src/main/resources/img/alerte.jpg");

            alerte.setAbsolutePosition(40f, 580f);

            alerte.scaleAbsolute(10, 10);
            doc.add(alerte);

            font.setColor(BaseColor.BLACK);
            doc.add(new Paragraph("    (votre dossier ne pourra être pris en compte sans cette référence)\n\n",font));
            Paragraph titre = new Paragraph("CONFIRMATION D’INSCRIPTION",font);
            titre.setAlignment(Element.ALIGN_CENTER);
            doc.add(titre);

            /*Paragraph par = new Paragraph("       Madame, Monsieur,\n " +
                    "     Nous avons le plaisir de vous confirmer l’inscription de votre enfant " + client.prenom_client.get()+" "+client.nom_client.get()+"\n"+
                    "     •  au centre de vacances de CV "+sejour.nom_centre.get()+",\n     •  pour le séjour du "+date.getValue()+"\n     •  pour une durée de "+sejour.duree.get()+" jours.\n"+
                    "     Les frais de séjour et de voyage s’élèvent  à : "+sejour.prix.get()+" €, auxquels s’ajoute le montant de l’adhésion, soit 3,00 € par famille et par année.\n\n"+
                    "     Vous avez versé des arrhes pour un montant de  50 € ainsi que l’adhésion de 3 € (sauf si elle a déjà été réglée lors d’un précédent séjour). Le solde est à régler entre trois et quatre semaines avant le départ. Nous vous rappelons que notre association est habilitée pour accepter les paiements par Chèques-Vacances.\n\n"+
                    "     Deux à trois semaines avant le départ, une fois le séjour soldé, nous vous ferons parvenir les documents suivants :\n"+
                    "     •  la convocation avec les lieux et heures de départ et retour\n"+
                    "     •  le dossier du jeune ou de l’enfant à compléter\n"+
                    "     •  la lettre du directeur vous donnant des informations sur le déroulement du séjour.\n"+
                    "     Nous vous rappelons que pour toutes les activités nautiques (voile, kayak, canyoning, surf, plongée…) le test de natation est obligatoire (le brevet de 25 ou 50 m est non valable).\n"+
                    "     Attention : Pour la plongée un certificat médical est également demandé.\n"+
                    "     Si vous êtes en possession de Bons-Vacances CAF ou MSA et si vous ne nous les avez pas encore transmis, veuillez nous les adresser dans les meilleurs délais (ou votre numéro d’allocataire pour les VACAF).\n"+
                    "     En souhaitant que votre enfant passe un agréable séjour en Centre de Vacances, nous vous adressons nos respectueuses salutations.",font2);

            doc.add(par);*/
            Paragraph dir = new Paragraph(" Le directeur: \nM. JOBERT",font);
            dir.setAlignment(Element.ALIGN_CENTER);
            doc.add(dir);


            doc.add(new Phrase("-------------------------------------------------------------------------------------------------------------------- "));
            Paragraph p = new Paragraph(" PAPILLON à DETACHER ET à RETOURNER A L’O.U.L.AVEC VOTRE REGLEMENT\n (si séjour NON SOLDÉ ou non pris en charge)",font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Phrase("NOM :",font2));
            doc.add(new Phrase(client.nom_client.get()+"\n",font));
            doc.add(new Phrase("REFERENCE :",font2));
            font.setSize(8);
            doc.add(new Phrase("159297 /  1112 - 8V0 /  CV "+sejour.nom_centre.get()+" du "+sejour.duree.get()+"\n",font));
            doc.add(new Phrase("     (votre dossier ne pourra être pris en compte sans cette référence)\n\n",font2));
            Image alerte1 = Image.getInstance("src/main/resources/img/alerte.jpg");

            alerte1.setAbsolutePosition(40f, 90f);

            alerte1.scaleAbsolute(10, 10);
            doc.add(alerte1);

            doc.add(new Paragraph("Le transport n’est pas compris pour les centres du Loiret, (INGRANNES, LES CAILLETTES, L’ETANG DU PUITS)\n"+
                    "vous devez conduire votre enfant sur place. Cependant un départ d’ORLEANS est possible (20 € ALLER/RETOUR),\n"+
                    "        le nombre de places étant limité, veuillez nous en informer par téléphone ou par courrier.",font));
            doc.close();
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
