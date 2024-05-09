package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.view.VagaPrivateProfileSerializableView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VagaMock {

    public static VagaPrivateProfileSerializableView getOne() {
        return getList().get(0);
    }

    public static List<VagaPrivateProfileSerializableView> getList() {
        return new ArrayList<>(
            List.of(
                new VagaPrivateProfileSerializableView(
                "1", OrgMock.getOne(), "Vaga de Estágio Auxiliar de Sala", "Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga",
                List.of("Estudante de Pedagogia"), List.of("matutino", "vespertino"), 20L, 1000L, List.of("medio"),
                List.of("Pedagodia"), null, null, null, null, null, LocalDateTime.now(), LocalDateTime.now(),
                0, List.of("3", "4"), 0, null),

                new VagaPrivateProfileSerializableView(
                "2", OrgMock.getOne(), "Vaga de Estágio Professor de Artes Educação Infantil", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dictum at tempor commodo ullamcorper a lacus. Faucibus et molestie ac feugiat sed lectus. Nulla pharetra diam sit amet nisl. Lectus proin nibh nisl condimentum id. Mauris pellentesque pulvinar pellentesque habitant morbi tristique senectus et. Faucibus ornare suspendisse sed nisi lacus. Mi bibendum neque egestas congue. Sit amet purus gravida quis blandit turpis cursus in. Amet est placerat in egestas. Sem viverra aliquet eget sit. Leo in vitae turpis massa sed elementum tempus egestas. Ultrices in iaculis nunc sed augue lacus. Sed viverra ipsum nunc aliquet bibendum enim facilisis gravida. Eu mi bibendum neque egestas congue quisque egestas diam in. Malesuada fames ac turpis egestas sed.\n" +
                "\n" +
                "Massa sapien faucibus et molestie. In hac habitasse platea dictumst. Penatibus et magnis dis parturient. Id aliquet risus feugiat in ante metus dictum. Metus vulputate eu scelerisque felis imperdiet proin fermentum. Ornare arcu dui vivamus arcu felis bibendum ut tristique. Platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Sagittis id consectetur purus ut faucibus pulvinar elementum integer. Rhoncus dolor purus non enim praesent elementum facilisis leo. Auctor eu augue ut lectus arcu. Aliquam sem fringilla ut morbi tincidunt augue interdum velit. Nisl suscipit adipiscing bibendum est ultricies. Enim nunc faucibus a pellentesque sit amet porttitor eget dolor. Sed libero enim sed faucibus turpis in eu mi bibendum. Sed tempus urna et pharetra pharetra massa massa. Dolor sit amet consectetur adipiscing elit ut aliquam purus sit.\n" +
                "\n" +
                "Iaculis eu non diam phasellus vestibulum. Libero justo laoreet sit amet. Purus in massa tempor nec feugiat. Augue lacus viverra vitae congue eu consequat. Consequat mauris nunc congue nisi vitae suscipit tellus mauris. Pharetra magna ac placerat vestibulum. Sed velit dignissim sodales ut eu. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque habitant. Velit ut tortor pretium viverra suspendisse. Vulputate eu scelerisque felis imperdiet. Ultrices vitae auctor eu augue ut lectus arcu bibendum. Viverra tellus in hac habitasse platea. Nec sagittis aliquam malesuada bibendum arcu vitae elementum. Mauris sit amet massa vitae tortor condimentum lacinia. Leo urna molestie at elementum eu.",
                List.of("Estudante de Pedagogia"), List.of("matutino"), 20L, 1000L, List.of("medio"),
                List.of("Pedagodia"), null, null, null, null, null, LocalDateTime.now(), LocalDateTime.now(),
                30, List.of("3", "4"), 0, null),

            new VagaPrivateProfileSerializableView(
                "3", OrgMock.getOne(), "Vaga de Desenvolver Nasa Full Stack Pleno Junior", "Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga",
                List.of("Estudante de TI", "Python", "Assembly Avançado", "15 anos de experiência"), List.of("matutino", "vespertino", "noturno"), 80L, 500L, List.of("superior"),
                List.of("Ciências da Computação", "Tecnologia da Informação"), LocalDate.now(), null, ContatoMock.getOne(), LocalizacaoMock.getOne(), List.of("http://linkedin.com/vagas_arrombadas"), LocalDateTime.now(), LocalDateTime.now(),
                0, List.of("3", "4"), 0, null),

            new VagaPrivateProfileSerializableView(
                "4", OrgMock.getOne(), "Vaga de Desenvolvedor Web", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dictum at tempor commodo ullamcorper a lacus. Faucibus et molestie ac feugiat sed lectus. Nulla pharetra diam sit amet nisl. Lectus proin nibh nisl condimentum id. Mauris pellentesque pulvinar pellentesque habitant morbi tristique senectus et. Faucibus ornare suspendisse sed nisi lacus. Mi bibendum neque egestas congue. Sit amet purus gravida quis blandit turpis cursus in. Amet est placerat in egestas. Sem viverra aliquet eget sit. Leo in vitae turpis massa sed elementum tempus egestas. Ultrices in iaculis nunc sed augue lacus. Sed viverra ipsum nunc aliquet bibendum enim facilisis gravida. Eu mi bibendum neque egestas congue quisque egestas diam in. Malesuada fames ac turpis egestas sed.\n",
                List.of("Javascript", "React.js"), List.of("matutino"), 15L, 700L, List.of("medio"),
                List.of("Tecnologia da Informação", "Design", "Ciências da Computação", "Desenvolvimento de Sistemas"), LocalDate.now().minusDays(40L), LocalDate.now().plusMonths(5L), ContatoMock.getOne(), null, null, LocalDateTime.now(), LocalDateTime.now(),
                0, List.of("3", "4"), 0, null)));
    }

    public static VagaPrivateProfileSerializableView getInvalid() {
        return new VagaPrivateProfileSerializableView();
    }


}
